package com.yijiu.newcoin.fragment;


import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.databinding.FragmentMineBinding;
import com.yijiu.newcoin.databinding.FragmentWalletBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by liao on 2017/5/28.
 */
public class WalletFragment extends BaseFragment implements View.OnClickListener {

    FragmentWalletBinding mBinding;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);

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
