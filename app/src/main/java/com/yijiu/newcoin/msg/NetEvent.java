package com.yijiu.newcoin.msg;

public class NetEvent {
    public boolean getConnectNetState() {
        return isConnectNet;
    }

  /*  public void setConnectNet(boolean connectNet) {
        isConnectNet = connectNet;
    }*/

    private boolean isConnectNet;

    public NetEvent(boolean b) {
        isConnectNet = b;
    }
}
