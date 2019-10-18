package com.yijiu.newcoin.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.yijiu.newcoin.R;


public class CustomProgressDialog extends Dialog {
    private Context context = null;
    private static CustomProgressDialog customProgressDialog = null;
    TextView tvMessage;
    public static int PROGRESS_TYPE = R.style.CustomProgressDialog;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }


    public static CustomProgressDialog createDialog(Context context) {
        customProgressDialog = new CustomProgressDialog(context,
                R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.customprogressdialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return customProgressDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }

        avLoadingIndicatorView = findViewById(R.id.avi);

        avLoadingIndicatorView.show();

    }

    @Override
    public void dismiss() {
        // TODO Auto-generated method stub
        if (avLoadingIndicatorView != null)
            avLoadingIndicatorView.hide();
        System.gc();
        super.dismiss();
    }

    /**
     * [Summary] setTitile 标题
     *
     * @return
     */
    public CustomProgressDialog setTitleText(String txt) {
        return customProgressDialog;
    }

    /**
     * [Summary] setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public void setMessage(String strMessage) {
        if (tvMessage == null) {
            tvMessage = (TextView) findViewById(R.id.tvMessage);
        }
        tvMessage.setText(strMessage);
    }

    //弹出加载中对话框
    public static Dialog showLoadingDialog(Context context, String loadingtxt,
                                           boolean isCanNotCancel, boolean cancelAble) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return null;
            }
        }
        Dialog mCustomProgressDialog = createDialog(context);
        mCustomProgressDialog.setCanceledOnTouchOutside(isCanNotCancel);
        mCustomProgressDialog.setCancelable(cancelAble);
        TextView tvMessage = (TextView) mCustomProgressDialog.findViewById(R.id.tvMessage);
        if (null != loadingtxt && null != tvMessage) {
            tvMessage.setText(loadingtxt);
        }
        // 对话框展示出来
        mCustomProgressDialog.show();
        return mCustomProgressDialog;
    }

}