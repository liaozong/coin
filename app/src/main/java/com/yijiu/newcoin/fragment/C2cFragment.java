package com.yijiu.newcoin.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.base.Constant;
import com.yijiu.newcoin.databinding.FragmentShopBinding;
import com.yijiu.newcoin.msg.EventMsg;
import com.yijiu.newcoin.msg.NetEvent;
import com.yijiu.newcoin.utils.PreferenceUtil;
import com.yijiu.newcoin.utils.UIUtils;
import com.yijiu.newcoin.utils.Utils;
import com.yijiu.newcoin.utils.ui.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by liao on 2017/5/28.
 */
public class C2cFragment extends BaseFragment {

    FragmentShopBinding mBinding;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false);

        BarUtils.setStatusBarLightMode(getActivity(), false);
        return mBinding.getRoot();
    }

    /*如果登录回来*/

    /*如果从这个页面过去  回来触发这个页面*/
    @Override
    protected void initData() {

        String string = PreferenceUtil.getString(Constant.TOKEN, "");
        if (string.equals("")) {
            if (!isFirstIn) {
//                Intent   intent = new Intent(getActivity(), LoginAty.class);
//                startActivity(intent);
            }
        } else {
            triggerLoading("one");
        }
    }


    @Override
    protected void initEvent() {
    }

    private boolean isFirstIn = true;

    @Override
    public void onResume() {
        super.onResume();

        boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
        if (!firstLogin)
            triggerLoading("one");

        if (isFirstIn) {
            isFirstIn = false;
            return;
        }


    }

    @Override
    public void loadingData(String requestType) {
//        UIUtils.loadingDialog(getActivity());
        switch (requestType) {
            case "one":

                break;
        }
    }



  /*  private PopupWindow iconpw;

    private void showPop() {
        View view = getLayoutInflater().inflate(R.layout.pop_nodevice, null);
        iconpw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        iconpw.setFocusable(false);
        iconpw.setOutsideTouchable(false);
        iconpw.setBackgroundDrawable(new BitmapDrawable());
        iconpw.setAnimationStyle(R.style.myanimation);
        iconpw.setWidth(UIUtils.dp2px(getActivity(), 330));
        iconpw.showAtLocation(view, Gravity.CENTER, 0, 100);
        iconpw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                UIUtils.setBackgroundAlpha(1.0f, getActivity());
            }
        });

        UIUtils.setBackgroundAlpha(0.5f, getActivity());


        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconpw.dismiss();
            }
        });

        view.findViewById(R.id.go_conn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                iconpw.dismiss();
            }
        });

    }*/


    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
    }

    /*新增选中分类的方法*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMsg event) {
        if (event.getType() != null && event.getType().equals("shoppingcart")) {
            String string = PreferenceUtil.getString(Constant.TOKEN, "");
        } else if (event.getType() != null && event.getType().equals("zhifu")) {
        } else if (event.getType() != null && event.getType().equals("main") && event.getWord().equals("resume") || event.getWord().equals("resumeshop")) {
            if (event.getWord().equals("resumeshop")) {
                String string = PreferenceUtil.getString(Constant.TOKEN, "");
                if (string.equals("")) {
                }
            }
            boolean firstLogin = PreferenceUtil.getBoolean(Constant.firstLogin, true);
            UIUtils.print("shoppingcar...event///" + firstLogin);
            if (!firstLogin)
                triggerLoading("one");

        } else if (event.getType() != null && event.getType().equals("cleancar")) {
        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNetEvent(NetEvent event) {
        boolean connectNetState = event.getConnectNetState();
        Utils.print("netWorkState", connectNetState + "");
        if (connectNetState) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
