package com.yijiu.newcoin.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseApplication;
import com.yijiu.newcoin.view.CustomProgressDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


public class UIUtils {

    //打印方法
    public static void print(String str) {

        System.out.println("liao...." + str);

    }

    public static void toast(final String str) {
        postTaskSafely(new Runnable() {
            @Override
            public void run() {
                Toasty.normal(getContext(), str, Toast.LENGTH_SHORT).show();
//                ToastUtils.show(str);
            }
        });
    }

    private static CustomProgressDialog mLoginDialog;

    public static void loadingDialog(Activity activity) {
        loadingDialog(activity, "加载中");
    }

    public static void loadingDialog(final Activity activity, final String content) {
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

    public static void disMisDialog(long l) {
        getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mLoginDialog != null)
                    mLoginDialog.dismiss();
            }
        }, l);
    }

    /**
     * 设置系统导航栏字体颜色
     */
    public static void setWindowTitleWordColor(Activity activity) {
        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    public static void setAtyTitleBgColor(Activity activity, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(id));
        }
    }

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return BaseApplication.getmContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中定义的字符
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到String.xml中定义的字符数组
     */
    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中定义的颜色值
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到主线程的id
     */
    public static long getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    /**
     * 得到主线中中的一个handler
     */
    public static Handler getMainThreadHandler() {
        return BaseApplication.getmHandler();
    }

    //检查字符串是否包含以下非法特殊字符
    public static boolean islegalInput(String txt) {
        String regEx = "[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(txt);
        return !m.find();
    }

    /**
     * 安全的执行一个task
     */
    public static void postTaskSafely(Runnable task) {
        // 得到当前线程的线程id
        long curThreadId = android.os.Process.myTid();
        /**
         T:thread
         P:process
         U:user
         */
        // 如果当前线程的线程id==主线程线程id
        if (curThreadId == getMainThreadId()) {
            task.run();
        } else { // 如果当前线程的线程id!=主线程线程id
//            getMainThreadHandler().post(task);
            getMainThreadHandler().postDelayed(task, 500);
        }

    }

    public static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static void setStatusBar(View view, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 设置状态栏透明
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusHeight(context));//宽度、高度
        view.setLayoutParams(params);
        view.setBackgroundColor(getColor(R.color.white));
//        view.setBackgroundResource(R.mipmap.topbg010);
    }

    /**
     * dp 转 px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        // 1px = 1dp * (dpi / 160)

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dpi = metrics.densityDpi;

        return (int) (dp * (dpi / 160f) + 0.5f);
    }

    /**
     * px 转 dp
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2dp(Context context, int px) {
        // 1dp = 1px * 160 / dpi

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        int dpi = metrics.densityDpi;
        return (int) (px * 160f / dpi + 0.5f);
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /*设置背景透明度*/
    public static void setBackgroundAlpha(float alpha, Activity activity) {
        if (activity != null) {
            WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
            lp.alpha = alpha;
            (activity).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            (activity).getWindow().setAttributes(lp);
        }
    }

    /*藏起软键盘*/
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断软键盘是否弹出
     */
    public boolean isSHowKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
            imm.showSoftInput(v, 0);
            return true;
            //软键盘已弹出
        } else {
            return false;
            //软键盘未弹出
        }
    }

    public boolean softInput(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        return true;

    }

    public static void buttonClick(final View v) {
        v.setEnabled(false);
        getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setEnabled(true);
            }
        }, 1000);
    }

    public static void buttonClick(final View v, long time) {
        v.setEnabled(false);
        getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setEnabled(true);
            }
        }, time);
    }

    public static boolean checkInput(String input) {
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
        Matcher m = pattern.matcher(input);
        if (!m.matches()) { //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;
    }
  /*  public static boolean checkInput(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,20}$");
        Matcher m = pattern.matcher(input);
        if( !m.matches() ){ //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;
    }*/
   /* public static String showWeek(String timedtask_days) {
        String time = "";
        if (timedtask_days.contains("1"))
            time = time + UIUtils.getString(R.string.Monday) + ",";
        if (timedtask_days.contains("2"))
            time = time + UIUtils.getString(R.string.Tuesday) + ",";
        if (timedtask_days.contains("3"))
            time = time + UIUtils.getString(R.string.Wednesday) + ",";
        if (timedtask_days.contains("4"))
            time = time + UIUtils.getString(R.string.Thursday) + ",";
        if (timedtask_days.contains("5"))
            time = time + UIUtils.getString(R.string.Friday) + ",";
        if (timedtask_days.contains("6"))
            time = time + UIUtils.getString(R.string.Saturday) + ",";
        if (timedtask_days.contains("7"))
            time = time + UIUtils.getString(R.string.Sunday) + ",";

        time = time.substring(0, time.length() - 1);
        return time;
    }*/


    public static void startAtyAnim(Activity activity, Intent intent, View view, String animName) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, view, animName);
            if (options != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.startActivity(intent, options.toBundle());
            } else {
                activity.startActivity(intent);
            }
        } else
            activity.startActivity(intent);
    }

}
