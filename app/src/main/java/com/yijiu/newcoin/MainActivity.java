package com.yijiu.newcoin;


import android.graphics.Color;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.databinding.ActivityMainBinding;
import com.yijiu.newcoin.factory.FragmentFactory;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAty {

    private ActivityMainBinding mBinding;

    @Override
    protected void initView() {
        super.initView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private String[] mMainButton;
    private String[] tabText = new String[5];

    @Override
    public void init() {
        super.init();
        mMainButton = UIUtils.getStringArr(R.array.main_button_arr);
        tabText[0] = mMainButton[0];
        tabText[1] = mMainButton[1];
        tabText[2] = mMainButton[2];
        tabText[3] = mMainButton[3];
        tabText[4] = mMainButton[4];
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        super.initData();

        setViewPager();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNetEvent(NetEvent event) {
        boolean connectNetState = event.getConnectNetState();
      /*  if (connectNetState)
            UIUtils.toast(MainActivity.this, UIUtils.getString(R.string.phoneInternet));
        else
            UIUtils.toast(MainActivity.this, UIUtils.getString(R.string.phonenoInternet));*/
    }


    @Override
    protected void loadingData(String requestType) {

    }

    private List<Fragment> fragments = new ArrayList<>();
    //未选中icon
    private int[] normalIcon = {R.mipmap.home_normal, R.mipmap.sort_normal, R.mipmap.shoppingcar_normal, R.mipmap.mine_normal, R.mipmap.mine_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.home_select, R.mipmap.sort_select, R.mipmap.shoppingcar_select, R.mipmap.mine_select, R.mipmap.mine_select};

    private void setViewPager() {
        fragments.add(FragmentFactory.createFragment(Constant.numzero));
        fragments.add(FragmentFactory.createFragment(Constant.numone));
        fragments.add(FragmentFactory.createFragment(Constant.numtwo));
        fragments.add(FragmentFactory.createFragment(Constant.numthree));
        fragments.add(FragmentFactory.createFragment(Constant.numfour));
        mBinding.navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .anim(Anim.ZoomIn)//点击tab动画
                .navigationHeight(61)  //导航栏高度
                .iconSize(20)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .normalTextColor(Color.parseColor("#DEDEDE"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#3BAC6A"))   //Tab选中时字体颜色
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {   //Tab点击事件  return true 页面不会切换
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        setSelected(position);
                        return false;
                    }
                })
                .build();
    }

    int currentPosition = Constant.numzero;

    //设置选中发出事件
    private void setSelected(int position) {
        UIUtils.print("request!!!selected" + position);
        currentPosition = position;
        /*这行第二个选中*/

        if (position == 2)
            EventBus.getDefault().post(new EventMsg("main", "resumeshop"));
        else if (position == 1)
            EventBus.getDefault().post(new EventMsg("main", "resumesort"));
        else if (position == 2)
            EventBus.getDefault().post(new EventMsg("main", "resumemine"));

    }
}
