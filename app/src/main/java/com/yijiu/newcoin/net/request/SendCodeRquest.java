package com.yijiu.newcoin.net.request;


import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.net.RetrofitUtils;
import com.yijiu.newcoin.net.SuperBaseRequest;
import com.yijiu.newcoin.utils.UIUtils;

import retrofit2.Call;

public class SendCodeRquest extends SuperBaseRequest {
    private String phone;

    public SendCodeRquest(String phone) {
        this.phone = phone;
        UIUtils.print("request!!!" + phone + "///");
    }

    @Override
    protected String String() {
        return Constant.CODE;
    }

    /*  http://39.98.191.42:8086/api/front/sendCode?mobile=15919738009&type=REGISTER*/
    @Override
    protected Call<String> Call() {
        return RetrofitUtils.getRequestServies().getVerifyCode(phone);//RequestData.getHeadMap(),
    }
}
