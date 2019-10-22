package com.yijiu.newcoin.fragment;


import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.yijiu.newcoin.R;
import com.yijiu.newcoin.adapter.WalletPagerAdapter;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.databinding.FragmentWalletBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liao on 2017/5/28.
 */
public class WalletFragment extends BaseFragment implements View.OnClickListener {

    FragmentWalletBinding mBinding;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);

        BarUtils.setStatusBarLightMode(getActivity(), false);
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
//        boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
        initTabLayout();


    }
    private List<String> datas = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private void initTabLayout() {
        datas.add("DILI");
        datas.add("USDT");
        datas.add("资产钱包");
        datas.add("通关文书");
        datas.add("平移钱包");
        //循环注入标签
        for (String tab : datas) {
            mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(tab));
        }
        //设置TabLayout点击事件
        for (int i = 0; i < datas.size(); i++) {

            fragments.add(MultiFragment.newInstance(i));
        }
        mBinding. tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mBinding.vpContent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        WalletPagerAdapter  adapter = new WalletPagerAdapter(getChildFragmentManager(), datas, fragments);
        mBinding.vpContent.setAdapter(adapter);
        mBinding.vpContent.setOffscreenPageLimit(datas.size());
        mBinding.tabLayout.setupWithViewPager(mBinding.vpContent);

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
