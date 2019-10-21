package com.yijiu.newcoin.activity.home;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.databinding.ActivitySystemSettingBinding;
import com.yijiu.newcoin.utils.ui.BarUtils;

public class KefuAty extends BaseAty {
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
                if (mBinding.WebView.canGoBack()) {
                    mBinding.WebView.goBack();
                } else {
                    finish();
                }
//                finish();
            }
        });

        WebSettings settings=mBinding.WebView.getSettings();
        /*最重要的是设置*/
        settings.setJavaScriptEnabled(true);
        /*默认是true  如果不改变 会阻塞页面  下载不出来图片*/
        settings.setBlockNetworkImage(false);
        /*可以有缓存*/
        settings.setAppCacheEnabled(true);
        /*通过mwebview  去加载地址  整个页面是*/
        mBinding.WebView.loadUrl("http://api.pop800.com/chat/542692");

        // mAppInterface=new WebAppInterface(this);
        /*第二个参数是名字  怎么写 appInterface要和html5  里面 window.appInterface.addToCart(id);  这保持一致*/
        // mBinding.WebView.addJavascriptInterface(mAppInterface,"appInterface");
//        mWebView.addJavascriptInterface(new WebAppInterface(this),
//                "android");
        /*调用这个监听*/
        mBinding.WebView.setWebViewClient(new WC());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /*这个类用来监听html5  页面有没有加载完成*/
    class WC extends WebViewClient {
        /*这个方法是点击空白部分   选择出来get和set方法的   选择重写
         * 这个是页面加载完成之后触发的
         * */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            /*页面加载完成再调用这个方法    同时我们进来这个页面的时候需要一个loading的效果
             *可以写原生  也可以写在html5里面  加载完成之后关闭dialog
             * */
//            if(mDialog!=null&&mDialog.isShowing())
//                mDialog.dismiss();
//
        }
    }
}
