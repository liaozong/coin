package com.yijiu.newcoin.activity;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.MainActivity;
import com.yijiu.newcoin.R;
import com.yijiu.newcoin.activity.login.LoginAty;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.databinding.ActivityStartBinding;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.UIUtils;
import com.yijiu.newcoin.utils.ui.BarUtils;


/**
 * Created by liao on 2018/3/14.
 */

public class StartAty extends BaseAty {

    private ActivityStartBinding mBinding;
    private Boolean firstOpenApp;
    private Boolean firstLogin;

    //权限组内只需要申请一个权限，组内的其它权限系统会自动同意view
    //private final int ACCESS_COARSE_LOCATION = 2;//允许一个程序访问CellID或WiFi热点来获取粗略的位置
    //private final int ACCESS_FINE_LOCATION = 1;//获取精确位置，通过GPS芯片接收卫星的定位信息，定位精度达10米以内
//    private final int ACCESS_COARSE_LOCATION = 1;

    @Override
    public void init() {
        super.init();
        UIUtils.setWindowTitleWordColor(this);
        BarUtils.setStatusBarColor(this,getResources().getColor(R.color.bg_color));
        BarUtils.setStatusBarLightMode(this,false);
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start);
    }

    @Override
    protected void initData() {
        super.initData();


//        checkPermission();

//        mBinding.setTitle("start");
//        mBinding.title.title.setTextColor(UIUtils.getColor(R.color.black));
    }

   /* //检查用户是否给予了权限，如果给予了，打开系统相册；如果没有给予权限，则向用户申请授权
    private void checkPermission() {
        // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
        //List<String> mPermissionList = new ArrayList<>();
        //String[] permissons = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
        if (!(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)) { //权限没有被授予

            //Toasty.success(context,"权限未拥有，需要申请权限").show();
            */

    /**
     * 申请授权
     *
     * @param
     *//*
            ActivityCompat.requestPermissions(StartAty.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    ACCESS_COARSE_LOCATION);

        } else {//权限被授予
            //Toasty.success(context,"已经拥有权限").show();
            //直接操作
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == ACCESS_COARSE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toasty.success(StartAty.this,"权限获取成功").show();

            } else { // Permission Denied

                Toasty.info(StartAty.this, "为了软件能正常运行，请前往设置页面手动授予软件定位权限").show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
*/
    @Override
    protected void initEvent() {

        firstOpenApp = PreferenceUtil.getBoolean(Constant.firstOpenApp, true);
//        firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
        UIUtils.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
            /*    if (firstOpenApp) {
                    startActivity(new Intent(StartAty.this, GuideView.class));
                    PreferenceUtil.commitBoolean(Constant.firstOpenApp, false);
                    finish();
                } else{
                        startActivity(new Intent(StartAty.this, MainActivity.class));
                        finish();
                }*/
                startActivity(new Intent(StartAty.this, LoginAty.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void loadingData(String requestType) {

    }
}
