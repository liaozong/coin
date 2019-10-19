package com.yijiu.newcoin.fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.databinding.FragmentTouziBinding;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



/**
 * Created by liao on 2017/5/28.
 * 分类页面
 */
public class TouziFragment extends BaseFragment {

    private FragmentTouziBinding mBinding;
    private Boolean aBoolean;

    /*定义下拉刷新的三种状态*/
    String url = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2899934234,1012362392&fm=26&gp=0.jpg";
    private Intent intent;
    private int categoryId;
    private Integer word;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_touzi, container, false);

//        UIUtils.setAtyTitleBgColor(getActivity(),R.color.bantouming);
//        UIUtils.setWindowTitleWordColor(getActivity());
        return mBinding.getRoot();
    }

    @Override
    protected void initData() {
//        mBinding.setTitle("请输入商品名称");
//        initSortRecyclerview();

        triggerLoading("one");
    }

    @Override
    protected void loadingData(String requestType) {

    }

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);

        BarUtils.setStatusBarLightMode(getActivity(), true);

      String  marketId = PreferenceUtil.getString("marketId", "");
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNetEvent(NetEvent event) {
        boolean connectNetState = event.getConnectNetState();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
