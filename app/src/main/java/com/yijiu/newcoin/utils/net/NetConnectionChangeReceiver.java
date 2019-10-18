package com.yijiu.newcoin.utils.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yijiu.newcoin.msg.NetEvent;

import org.greenrobot.eventbus.EventBus;

public class NetConnectionChangeReceiver extends BroadcastReceiver {

    NetUtils.NetWorkListener listener ;

    public NetConnectionChangeReceiver(NetUtils.NetWorkListener listener){
        this.listener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            listener.onDisconnected();
            EventBus.getDefault().post(new NetEvent(false));
        }else {
            listener.onConnected();
            EventBus.getDefault().post(new NetEvent(true));
        }
    }
}
