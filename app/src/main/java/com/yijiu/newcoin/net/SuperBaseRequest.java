package com.yijiu.newcoin.net;

import android.content.Intent;


import com.yijiu.newcoin.R;
import com.yijiu.newcoin.activity.login.LoginAty;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.factory.FragmentFactory;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.LogoutEvent;
import com.yijiu.newcoin.net.NetUtil;
import com.yijiu.newcoin.net.RetrofitUtils;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;

import static com.yijiu.newcoin.base.BaseAty.exit;


/**
 * Created by Administrator on 2018/12/18.
 */

public abstract class SuperBaseRequest {
    private String error_msg = "";

    public String toLoadData() throws Exception {

        Call<String> call = Call();
        if (!NetUtil.isNetworkConnected()) {
            UIUtils.toast(UIUtils.getString(R.string.net_error));
            return null;
        }
        UIUtils.print("request!!!isNet" + NetUtil.isNetworkConnected());

        if (call == null) {
            UIUtils.print("request!!!//call==null");
            UIUtils.toast(UIUtils.getString(R.string.loginagain));
            return null;
        }

        Response<String> execute = call.execute();
        int code = execute.code();
        UIUtils.print("request!!!code..." + code);
        if (code == 403 || code == 401) {

            LogoutEvent event = new LogoutEvent();
            event.setLogout(true);
            EventBus.getDefault().post(new EventMsg(Constant.LOGINAGAIN));
            // EventBus.getDefault().post(event);
            PreferenceUtil.saveUser(UIUtils.getContext(), null);
            PreferenceUtil.commitString(Constant.TOKEN, "");
            UIUtils.getContext().startActivity(new Intent(UIUtils.getContext(), LoginAty.class));
        }
        String body = execute.body();
        if (body == null) {
            UIUtils.print("request!!!//body==null");

            return null;

        }
        UIUtils.print("request!!!//" + call.request().toString());
//        UIUtils.print("request!!!" + call.request().headers());
        UIUtils.print("request!!!" + body);
        JSONObject jsonObject = new JSONObject(body);
        final String resultcode = jsonObject.getString(Constant.CODE);
        if (resultcode.equals(RetrofitUtils.SUCCESS)) {
            if (String().equals(Constant.ALL))
                return body;
            else
                return jsonObject.getString(String());
        } else if (resultcode.equals(Constant.TOKEN_LOSE) || resultcode.equals("403")) {
            UIUtils.toast("请重新登陆");
            exit();
            LogoutEvent event = new LogoutEvent();
            event.setLogout(true);
            FragmentFactory.fragmentMaps.clear();
            EventBus.getDefault().post(event);
            PreferenceUtil.commitBoolean(Constant.firstLogin, true);
            PreferenceUtil.saveUser(UIUtils.getContext(), null);
            PreferenceUtil.commitString(Constant.TOKEN, "");
            UIUtils.getContext().startActivity(new Intent(UIUtils.getContext(), LoginAty.class));
        } else {
            error_msg = jsonObject.getString(Constant.MSG);
            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    UIUtils.toast(error_msg);

                    if (resultcode.equals("nopaypwd")) {
                        EventBus.getDefault().post(new EventMsg("zhifumima", "1"));
                    }
                }
            });

        }
        if (isNotNeedCode()) {
            return body;
        }
        return null;
    }

    protected boolean isNotNeedCode() {
        return false;
    }

    protected String code(String success) {
        return success;
    }

    protected abstract String String();

    protected abstract Call<String> Call();


}