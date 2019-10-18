package com.yijiu.newcoin.net.request;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.bean.UserInfoModel;
import com.yijiu.newcoin.net.BaseBeanRequest;
import com.yijiu.newcoin.net.NetUtil;
import com.yijiu.newcoin.net.RetrofitUtils;
import com.yijiu.newcoin.utils.UIUtils;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/12/18.
 */

public class LoginRequest extends BaseBeanRequest<UserInfoModel> {
    RequestBody pack;

    public LoginRequest(Map loginParme) {
        String s1 = JSON.toJSONString(loginParme);
        pack = NetUtil.getPack(s1);
    }

    @Override
    protected UserInfoModel parseJson(String jsonString) {
        UIUtils.print("request!!!return..jsonString.." + jsonString);
        return new Gson().fromJson(jsonString, UserInfoModel.class);
    }

    @Override
    protected String setString() {
        return Constant.DATA;
    }

    @Override
    protected Call<String> setCall() {
        return RetrofitUtils.getRequestServies().login(pack);
    }

}
