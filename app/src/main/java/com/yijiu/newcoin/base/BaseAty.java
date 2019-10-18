package com.yijiu.newcoin.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.yijiu.newcoin.MainActivity;
import com.yijiu.newcoin.factory.ThreadPoolFactory;
import com.yijiu.newcoin.utils.UIUtils;
import com.yijiu.newcoin.view.CustomProgressDialog;

import java.util.LinkedList;

/**
 * Created by liao on 2017/12/12.
 */
public abstract class BaseAty extends FragmentActivity {
    public static LinkedList<BaseAty> allActivitys = new LinkedList<BaseAty>();
    private long mPreClickTime;
    public final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        allActivitys.add(this);

        inits(savedInstanceState);
        init();
        initView();
        initData();
        initDatas(savedInstanceState);
        initEvent();
    }

    protected void inits(Bundle savedInstanceState) {
    }

    protected void initDatas(Bundle savedInstanceState) {

    }

    public void init() {

    }

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    public BaseAty mTopActivity;

    public BaseAty getTopActivity() {
        return mTopActivity;
    }

    @Override
    protected void onResume() {
        mTopActivity = this;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        allActivitys.remove(this);
        super.onDestroy();

    }

    /**
     * 完成退出
     */
    public static void exit() {
        for (BaseAty activity : allActivitys) {
            activity.finish();
        }
    }

    public void exitNoMain() {
        for (BaseAty activity : allActivitys) {
            if (this instanceof MainActivity) {

            } else
                activity.finish();
        }

    }

    @Override
    public void onBackPressed() {
        // 是否是主要
        if (this instanceof MainActivity) {// 主页
            if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次按下的时间间隔大于2s钟
//                UIUtils.toast(UIUtils.getString(R.string.pre_exit));
                mPreClickTime = System.currentTimeMillis();
                return;
            } else {// 用户点击速度非常快
                exit();
                System.exit(0);//结束整个com.suren.isuke进程（DEAD状态）
            }
        } else {
            super.onBackPressed();
        }
    }

    //触发加载数据
    public void triggerLoading(final String requestType) {
        ThreadPoolFactory.getNormalThread().executeTask(new Runnable() {
            @Override
            public void run() {
                loadingData(requestType);

            }
        });
    }

    private CustomProgressDialog mLoginDialog;

    public void loadingDialog(Activity activity) {
        loadingDialog(activity, "加载中");
    }

    public void loadingDialog(final Activity activity, final String content) {
        UIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                if (mLoginDialog == null || mLoginDialog != null && !mLoginDialog.isShowing()) {
                    mLoginDialog = CustomProgressDialog.createDialog(activity);
                    mLoginDialog.setCancelable(true);
                    mLoginDialog.setCanceledOnTouchOutside(false);
                    mLoginDialog.setMessage(content);
                    mLoginDialog.show();
                    //1s后强制把对话框dimiss掉

                    disMisDialog(1000);
                }
            }
        });
    }

    public void disMisDialog(long l) {
        UIUtils.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mLoginDialog != null)
                    mLoginDialog.dismiss();
            }
        }, l);
    }

    protected abstract void loadingData(String requestType);
}
