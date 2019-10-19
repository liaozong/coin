package com.yijiu.newcoin.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yijiu.newcoin.MainActivity;
import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseApplication;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.databinding.FragmentHomeBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.UIUtils;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.country.RxLocationTool;
import com.yijiu.newcoin.utils.ui.BarUtils;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.darsh.multipleimageselect.helpers.Constants.REQUEST_CODE;

/**
 * Created by liao on 2017/5/28.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private FragmentHomeBinding mBinding;
    private Intent intent;
    private MainActivity mainActivity;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        BarUtils.setStatusBarLightMode(getActivity(), false);
        mainActivity = (MainActivity) getActivity();
        return mBinding.getRoot();
    }

    @Override
    protected void initData() {

        startGps();

        initBanner();
        mBinding.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMsg("leftopen", "1"));
            }
        });
    }

    private void toLocation() {
        RxLocationTool.registerLocation(getActivity(), 3000, 100, new RxLocationTool.OnLocationChangeListener() {
            @Override
            public void getLastKnownLocation(Location location) {
                UIUtils.print("Location///" + location.getLatitude() + "..." + location.getLongitude());
                showLocation(location);
            }

            @Override
            public void onLocationChanged(Location location) {
                UIUtils.print("Location///...." + location.getLatitude() + "..." + location.getLongitude());
                showLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                UIUtils.print("Location///provider" + provider);
            }
        });
    }

    @Override
    protected void initEvent() {

        /*mBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                triggerLoading("all");
                refreshlayout.finishRefresh(2000*//*,false*//*);//传入false表示刷新失败
            }
        });*/
    }

    public static String marketId;
    String productId = "1";

    @Override
    protected void loadingData(String requestType) {

        mainActivity.loadingDialog(getActivity());
        try {
            switch (requestType) {
                case "all":/*获取首页全部数据*/
//                    mBinding.smartRefreshLayout.finishRefresh(0);
                    mainActivity.disMisDialog(0);
                    break;
                case "one":
                    stopLocation();
                    mainActivity.disMisDialog(0);
                    break;
            }


        } catch (Exception e) {

            UIUtils.print("Exception....." + e.toString());
            UIUtils.disMisDialog(0);
        }
    }


    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
        BarUtils.setStatusBarLightMode(getActivity(), true);
        ZXingLibrary.initDisplayOpinion(getActivity());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNetEvent(NetEvent event) {
        boolean connectNetState = event.getConnectNetState();
        Utils.print("netWorkState", connectNetState + "");
        if (connectNetState) {

        }
    }

    /*定位那边传过来的值*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMsg(EventMsg event) {

        if (event.getType() != null && event.getType().equals("location")) {
            triggerLoading("all");
            stopLocation();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        triggerLoading("all");
//        startGps();
        mBinding.banner.start();//开始轮播
    }

    @Override
    public void onPause() {
        super.onPause();
//        stopLocation();
        mBinding.banner.pause();//暂停轮播
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        stopLocation();
    }

    private String Mac = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null)
                    return;
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();

                    if (result.contains("isuke:")) {
                        int i = result.indexOf("+");
                        Mac = result.substring(i + 1, result.length());
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(getActivity(), UIUtils.getString(R.string.parseerror), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;//网络定位
        } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;//GPS定位
        } else {
            Toast.makeText(HomeFragment.this.getActivity(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    //gps
    private LocationManager gpsManager;

    private void startGps() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        int checkPermission = ContextCompat.checkSelfPermission( HomeFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);

            PackageManager pm = getActivity().getPackageManager();
            boolean permissionACCESS_COARSE_LOCATION = (PackageManager.PERMISSION_GRANTED ==
                    pm.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, getActivity().getPackageName()));

            boolean permissionACCESS_FINE_LOCATION = (PackageManager.PERMISSION_GRANTED ==
                    pm.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, getActivity().getPackageName()));
            if (!permissionACCESS_COARSE_LOCATION && !permissionACCESS_FINE_LOCATION) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                Log.d("TTTT", "弹出提示");
                return;
            } else {
                toLocation();
            }
        } else {
            toLocation();
        }

    }

    // 创建位置监听器
    private LocationListener gpsListener = new LocationListener() {

        // 位置发生改变时调用
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);

        }


        // provider失效时调用
        @Override
        public void onProviderDisabled(String provider) {
            Utils.print("Location", "onProviderDisabled" + provider);
        }

        // provider启用时调用
        @Override
        public void onProviderEnabled(String provider) {
            Utils.print("Location", "onProviderEnabled" + provider);
        }

        // 状态改变时调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Utils.print("Location", "onStatusChanged");
        }
    };


    private void showLocation(Location location) {
        Utils.print("Location", "onLocationChanged");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        float speed = location.getSpeed();
        long time = location.getTime();
        String s = "latitude--->" + latitude
                + "  longitude--->" + longitude
                + "  speed--->" + speed
                + "  time--->" + new Date(time).toLocaleString();
        Log.e("gzq", "s：" + s);
        String sAddress = "";
//            Geocoder geocoder = new Geocoder(HomeFragment.this.getActivity());
        Geocoder geocoder = new Geocoder(BaseApplication.getmContext());
        boolean flag = Geocoder.isPresent();
        if (flag) {
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0) {
                    Address address = addresses.get(0);

                    if (!TextUtils.isEmpty(address.getLocality())) {
                        if (!TextUtils.isEmpty(address.getFeatureName())) {
                            //市和周边地址
                            sAddress = address.getLocality();
//                                sAddress = address.getLocality() + " " + address.getFeatureName();
                        } else {
                            sAddress = address.getLocality();
                        }
                    } else {
                        sAddress = "定位失败";
                    }
                    Log.e("gzq", "sAddress：" + address.getAdminArea());
                    Log.e("gzq", "sAddress：" + sAddress);
                    Log.e("gzq", "sAddress：" + sAddress.toString());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        PreferenceUtil.commitString("sAddress", sAddress);
        UIUtils.print("request!!!sAddress//////////////////////////" + sAddress);
        triggerLoading("one");
        /*定位提示框也只弹出一次*/
        UIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
//                    showPop();
            }
        });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Utils.print("Location", "onRequestPermissionsResult" + permissions.toString());
        switch (requestCode) {
            case 1:
                if (permissions.length != 0)
                    switch (permissions[0]) {
                        case Manifest.permission.ACCESS_FINE_LOCATION://权限1
                            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                toLocation();
//                                initGpsManager();
                            } else {
                                UIUtils.toast("You denied the permission");
                            }
                            break;
                        case Manifest.permission.ACCESS_COARSE_LOCATION://权限2
                            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                toLocation();
//                                initGpsManager();
                            } else {
                                UIUtils.toast("You denied the permission");
                            }
                            break;
                        default:
                    }
                break;
            default:
        }
    }

    private void stopLocation() {
        if (gpsListener != null && gpsManager != null) {
            gpsManager.removeUpdates(gpsListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ic_location:
//                break;
        }
    }
    private void initBanner() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);

//        mBinding.recycler.setMoveSpeed(1);
//        webBannerAdapter = new WebBannerAdapter(getActivity(), list);
//        mBinding.recycler.setAdapter(webBannerAdapter);


        mBinding.banner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

    }

    public class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
//            mImageView.setImageResource(data);
            RequestOptions requestOptions = new RequestOptions()
//                        .circleCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher);
            Glide.with(context)
                    .load(data)
                    .apply(requestOptions)
                    .into(mImageView);
        }
    }


}
