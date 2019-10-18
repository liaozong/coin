package com.yijiu.newcoin.utils.net;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.yijiu.newcoin.utils.UIUtils;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by liao on 2017/11/27.
 */

public class NetUtils {
    private static boolean isnetwork = false;

    public static void registerNetworkChanged() {


        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetConnectionChangeReceiver netChangeReceiver = new NetConnectionChangeReceiver(new NetWorkListener() {
            @Override
            public void onConnected() {
                isnetwork = true;
            }

            @Override
            public void onDisconnected() {
                isnetwork = false;
            }
        });
        UIUtils.getContext().registerReceiver(netChangeReceiver, filter);

    }

    public static void registerNetworkChangedReceiver() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetConnectionChangeReceiver netChangeReceiver = new NetConnectionChangeReceiver(new NetWorkListener() {
            @Override
            public void onConnected() {
                isnetwork = true;
            }

            @Override
            public void onDisconnected() {
                isnetwork = false;
            }
        });
        UIUtils.getContext().registerReceiver(netChangeReceiver, filter);

    }

    public static boolean getNetWorkState() {
        return isnetwork;
    }

    public interface NetWorkListener {

        void onConnected();

        void onDisconnected();
    }

    /*跳到系统开启wifi页面*/
    public static String getConnectWifiSsid(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getSSID();
    }
}
