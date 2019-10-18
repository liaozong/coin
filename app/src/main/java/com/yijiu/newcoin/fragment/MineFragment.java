package com.yijiu.newcoin.fragment;


import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.databinding.FragmentMineBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * Created by liao on 2017/5/28.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    FragmentMineBinding mBinding;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);

        BarUtils.setStatusBarLightMode(getActivity(), true);
        return mBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();

        boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
        if (!firstLogin) {
            /*网络不好的情况取这边数据*/
            triggerLoading("2");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initData() {
        boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
    }


    @Override
    protected void initEvent() {

    }

    String yqm = "";

    @Override
    protected void loadingData(String requestType) {
        switch (requestType) {
            case "1":

                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                try {


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "3":
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
        if (firstLogin) {

            return;
        }
        switch (v.getId()) {

        }
    }


//   private PopupWindow iconpw;
//
//    private void showPop() {
//        View view = getLayoutInflater().inflate(R.layout.pop_nodevice, null);
//        iconpw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
//        iconpw.setFocusable(false);
//        iconpw.setOutsideTouchable(false);
//        iconpw.setBackgroundDrawable(new BitmapDrawable());
//        iconpw.setAnimationStyle(R.style.myanimation);
//        iconpw.setWidth(UIUtils.dp2px(getActivity(), 330));
//        iconpw.showAtLocation(view, Gravity.CENTER, 0, 100);
//        iconpw.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                UIUtils.setBackgroundAlpha(1.0f, getActivity());
//            }
//        });
//
//        UIUtils.setBackgroundAlpha(0.5f, getActivity());
//
//
//        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                iconpw.dismiss();
//            }
//        });
//
//        view.findViewById(R.id.go_conn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
//                iconpw.dismiss();
//            }
//        });
//
//    }*/


    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNetEvent(NetEvent event) {
        boolean connectNetState = event.getConnectNetState();
        Utils.print("netWorkState", connectNetState + "");
        if (connectNetState) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMsg event) {
        if (event.getType() != null && event.getType().equals("main") && event.getWord().equals("resume") || event.getWord().equals("resumemine")) {
            boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
            if (!firstLogin)
                triggerLoading("2");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
