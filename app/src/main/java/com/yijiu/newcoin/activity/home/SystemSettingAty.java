package com.yijiu.newcoin.activity.home;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.databinding.ActivitySystemSettingBinding;
import com.yijiu.newcoin.databinding.ActivityUserinfoBinding;
import com.yijiu.newcoin.utils.ui.BarUtils;

public class SystemSettingAty extends BaseAty {
    private ActivitySystemSettingBinding mBinding;
    @Override
    protected void loadingData(String requestType) {

    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void initView() {
        super.initView();

        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.night1_word));
        BarUtils.setStatusBarLightMode(this, false);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_system_setting);
    }

    @Override
    protected void initData() {
        super.initData();

        mBinding.inTitle.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
