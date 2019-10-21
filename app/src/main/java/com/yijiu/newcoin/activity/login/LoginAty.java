package com.yijiu.newcoin.activity.login;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.MainActivity;
import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.bean.UserInfoModel;
import com.yijiu.newcoin.databinding.ActivityLoginBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.net.RequestData;
import com.yijiu.newcoin.net.request.LoginRequest;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.UIUtils;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wyliu on 2017/1/12.
 * 登录
 */
public class LoginAty extends BaseAty {

    private ActivityLoginBinding mBinding;
    private String username;
    private String pwd;
    private String id = "1";

    @Override
    public void init() {
        super.init();
        UIUtils.setWindowTitleWordColor(this);
        BarUtils.setStatusBarColor(this,getResources().getColor(R.color.bg_color));
        BarUtils.setStatusBarLightMode(this,false);
    }

    @Override
    protected void initView() {
        super.initView();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        PreferenceUtil.commitBoolean(Constant.firstLogin, true);
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().post(new EventMsg("cartListBeanNum", "0"));

        EventBus.getDefault().post(new EventMsg("main", "resumemine"));
        EventBus.getDefault().post(new EventMsg("cleancar", ""));
//        boolean netWorkState = NetUtils.getNetWorkState();
//        Utils.print("netWorkState", netWorkState + "");
    }

    public static String newacount = "";
    public static String newpass = "";
    public static boolean isRemeber = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isRemeber) {
            mBinding.acount.setText(newacount);
            mBinding.etPassword.setText(newpass);

            isRemeber = false;
        }

    }

    private boolean isAcountYes = false;
    private boolean isPwdYes = false;
    private boolean ischose = true;

    @Override
    protected void initEvent() {
        super.initEvent();

        mBinding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.buttonClick(mBinding.login);
//                checkEdit();
                startActivity(new Intent(LoginAty.this, MainActivity.class));
                finish();
            }
        });
        mBinding.forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.buttonClick(mBinding.login);
                Intent intent = new Intent(LoginAty.this, ForgetAty.class);
               /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginAty.this, mBinding.login, "for");
                    if (options != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                } else*/
                    startActivity(intent);
            }
        });

        mBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAty.this, RegisterAty.class));
            }
        });


        mBinding.acount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = s.toString();
                /*首选判断是不是手机号*/
                if (phone.length() > 5) {
                    isAcountYes = true;
                    if (isPwdYes) {
                        mBinding.login.setClickable(true);
                    }
//                    mBinding.tvErrorTips.setText("");
                } else {
//                    mBinding.tvErrorTips.setText(UIUtils.getString(R.string.registeraty_phone_error));
                    isAcountYes = false;
                    mBinding.login.setClickable(true);
                }


            }
        });
        mBinding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                /*判断登录密码是否大于六位数*/
                String pwd = s.toString().trim();
                if (pwd.length() >= 6) {
                    isPwdYes = true;
                    if (isAcountYes) {
                        mBinding.login.setClickable(true);
                    }
//                    mBinding.tvErrorTips.setText("");
                } else {
//                    mBinding.tvErrorTips.setText(UIUtils.getString(R.string.new_pwd_length));
                    isPwdYes = false;
                    mBinding.login.setClickable(true);
                }
            }
        });

    }

    @Override
    protected void loadingData(String requestType) {
        UIUtils.print("clickLogin2");
        requestLogin(username, pwd);
    }

    private void checkEdit() {
        username = mBinding.acount.getText().toString();
        pwd = mBinding.etPassword.getText().toString();
        if (username.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.verify_phone_num));
            return;
        } else if (!Utils.isPhoneNum(username)) {
            UIUtils.toast(UIUtils.getString(R.string.registeraty_phone_error));
            return;
        } else if (pwd.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.forget_edit_pwd));
            return;
        } else if (pwd.length() < 6) {
            UIUtils.toast(UIUtils.getString(R.string.new_pwd_length));
            return;
        }/* else if (!UIUtils.checkInput(pwd)) {
            UIUtils.toast(getString(R.string.register_hint_pwd));
            return;
        }*/
//        if (ischose == true) {
        triggerLoading("");
//        } else if (ischose == false) {
//            UIUtils.toast(UIUtils.getString(R.string.login_no_shfwxy));
//        }

    }


    private void requestLogin(String username, String pwd) {
        try {
            PreferenceUtil.commitString("useracount", username);
            /*手机号码. ,验证码,还有国家号*/
            dealUserInfo(new LoginRequest(RequestData.login(username, pwd)).loadData());
        } catch (Exception e) {
            UIUtils.print("request!!!..." + e.toString());
            UIUtils.toast(getString(R.string.request_failed));
        }
    }

    private void dealUserInfo(UserInfoModel appUserInfo) {
        if (appUserInfo != null) {
            UIUtils.print("appuserinfo..." + appUserInfo.toString());
            PreferenceUtil.saveUser(LoginAty.this, appUserInfo);
//            EventBus.getDefault().postSticky(new EventMsg("shoppingcart", "0"));
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
