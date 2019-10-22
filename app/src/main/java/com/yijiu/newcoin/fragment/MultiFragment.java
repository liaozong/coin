package com.yijiu.newcoin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.databinding.FragmentMultiWalletBinding;

public class MultiFragment extends BaseFragment {

    private FragmentMultiWalletBinding mBinding;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_multi_wallet, container, false);


        return mBinding.getRoot();
    }

    int mPage;

    @Override
    protected void init() {
        super.init();
        mPage = getArguments().getInt("page");


    }

    @Override
    protected void initData() {
        super.initData();

        setVisiable();
    }

    private void setVisiable() {
        mBinding.tvNoOpen.setVisibility(View.GONE);
        if (mPage == 0) {
            mBinding.llUsdt.setVisibility(View.GONE);
            mBinding.tvItemHuazhuan.setVisibility(View.GONE);
        } else if (mPage == 1) {
            mBinding.llDili.setVisibility(View.GONE);
        }else if (mPage==2){
            mBinding.tvItemChongzhi.setVisibility(View.GONE);
            mBinding.tvItemTixian.setVisibility(View.GONE);
        }else {
            mBinding.tvNoOpen.setVisibility(View.VISIBLE);
            mBinding.llAll.setVisibility(View.GONE);
        }
    }

    @Override
    protected void loadingData(String requestType) {

    }

    public static MultiFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        MultiFragment fragment = new MultiFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
