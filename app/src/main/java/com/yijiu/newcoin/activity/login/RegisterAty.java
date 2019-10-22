package com.yijiu.newcoin.activity.login;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.bean.UserInfoModel;
import com.yijiu.newcoin.databinding.ActivityRegisterBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.net.RequestData;
import com.yijiu.newcoin.net.RetrofitUtils;
import com.yijiu.newcoin.net.request.LoginRequest;
import com.yijiu.newcoin.net.request.RegisterRequest;
import com.yijiu.newcoin.net.request.SendCodeRquest;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.UIUtils;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wyliu on 2017/1/12.
 * 登录
 */
public class RegisterAty extends BaseAty {

    private ActivityRegisterBinding mBinding;
    private String username;
    private String pwd;
    private String paypwd;
    private String verfycode;
    private String invatecode;
    private String id = "1";

    @Override
    public void init() {
        super.init();
        UIUtils.setWindowTitleWordColor(this);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.bg_color));
        BarUtils.setStatusBarLightMode(this, false);
    }

    @Override
    protected void initView() {
        super.initView();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
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
                checkEdit();
            }
        });


        mBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        //    mBinding.login.setBackground(getDrawable(R.drawable.login_buttom));
                        mBinding.login.setClickable(true);
                    }
                    mBinding.tvErrorTips.setText("");
                } else {
                    mBinding.tvErrorTips.setText(UIUtils.getString(R.string.registeraty_phone_error));
                    isAcountYes = false;
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                        mBinding.login.setBackground(getDrawable(R.drawable.login_buttom_noclick));
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
                    mBinding.tvErrorTips.setText("");
                } else {
                    mBinding.tvErrorTips.setText(UIUtils.getString(R.string.new_pwd_length));
                    isPwdYes = false;
                    mBinding.login.setClickable(true);
                }
            }
        });

        mBinding.tvGetVerfyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.buttonClick(v);
                triggerLoading("getcode");
            }
        });

    }

    @Override
    protected void loadingData(String requestType) {
        UIUtils.print("clickLogin2");

        try {
            if (requestType.equals("getcode")) {
                String phone = mBinding.acount.getText().toString();
                String verification = new SendCodeRquest(phone).toLoadData();
                /*这个地方为空*/
                Log.e("----------", "--------verification" + verification);
                if (verification != null && verification.equals(RetrofitUtils.SUCCESS))
                    timer.start();
            } else
                requestLogin(username, pwd);
        } catch (Exception e) {

        }


    }

    private void checkEdit() {
        username = mBinding.acount.getText().toString().trim();
        pwd = mBinding.etPassword.getText().toString().trim();
        paypwd = mBinding.etSafePassword.getText().toString().trim();
        verfycode = mBinding.etVerifyCode.getText().toString().trim();
        invatecode = mBinding.etInvitationCode.getText().toString().trim();
        if (username.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.verify_phone_num));
            return;
        } else if (!Utils.isPhoneNum(username)) {
            UIUtils.toast(UIUtils.getString(R.string.registeraty_phone_error));
            return;
        } else if (pwd.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.forget_edit_pwd));
            return;
        } else if (pwd.length() < 8) {
            UIUtils.toast(UIUtils.getString(R.string.new_pwd_length));
            return;
        } else if (paypwd.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.edit_pay_pwd));
            return;
        } else if (paypwd.length() < 6) {
            UIUtils.toast(UIUtils.getString(R.string.pay_pwd_length));
            return;
        } else if (invatecode.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.edit_invatecode));
            return;
        } else if (verfycode.length() == 0) {
            UIUtils.toast(UIUtils.getString(R.string.edit_verfycode));
            return;
        }
        triggerLoading("");

    }


    private void requestLogin(String username, String pwd) {
        try {
            PreferenceUtil.commitString("useracount", username);
            /*手机号码. ,验证码,还有国家号*/
            dealUserInfo(new RegisterRequest(RequestData.register(verfycode, username, pwd, paypwd, invatecode)).loadData());
        } catch (Exception e) {
            UIUtils.print("request!!!..." + e.toString());
            UIUtils.toast(getString(R.string.request_failed));
        }
    }

    private void dealUserInfo(UserInfoModel appUserInfo) {
        if (appUserInfo != null) {
            UIUtils.print("appuserinfo..." + appUserInfo.toString());
            PreferenceUtil.commitBoolean(Constant.firstLogin, false);

            PreferenceUtil.commitString(Constant.TOKEN, appUserInfo.getToken());
            PreferenceUtil.commitString(Constant.DISCOUNT, appUserInfo.getUser().getDiscount() + "");
            PreferenceUtil.commitString("usdt", appUserInfo.getUser().getUsdtBalance() + "");
            PreferenceUtil.commitString("cny", appUserInfo.getUser().getCny() + "");
            PreferenceUtil.commitString("score", appUserInfo.getUser().getScore() + "");
            PreferenceUtil.commitString("userName", appUserInfo.getUser().getUserName() + "");
            PreferenceUtil.commitInt(Constant.LEVEL, appUserInfo.getUser().getLevel());
            PreferenceUtil.commitString(Constant.USDTADDRESS, appUserInfo.getUser().getUsdtAddress() + "");

            /*usdt换算汇率*/
            PreferenceUtil.commitString(Constant.USDTRATE, appUserInfo.getUser().getUsdtRate() + "");
            /*存这个值 购物车数量详情GoodsDetailAty  用到*/
            PreferenceUtil.commitString(Constant.CARTSIZE, appUserInfo.getUser().getCartSize() + "");
            UIUtils.print("request!!!appuserinfo..." + appUserInfo.getToken());
            PreferenceUtil.saveUser(RegisterAty.this, appUserInfo);

            EventBus.getDefault().postSticky(new EventMsg("shoppingcart", "0"));
            finish();
        }
    }

    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mBinding.tvGetVerfyCode.setEnabled(false);
            mBinding.tvGetVerfyCode.setText(getString(R.string.sended) + "(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            mBinding.tvGetVerfyCode.setEnabled(true);
            mBinding.tvGetVerfyCode.setText(getString(R.string.re_send_code));
        }
    };

}
