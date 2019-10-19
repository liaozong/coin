package com.yijiu.newcoin.factory;


import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.fragment.HomeFragment;
import com.yijiu.newcoin.fragment.AppFragment;
import com.yijiu.newcoin.fragment.C2cFragment;
import com.yijiu.newcoin.fragment.TouziFragment;
import com.yijiu.newcoin.fragment.WalletFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liao on 2017/5/28.
 * 工厂类，用于创建fragment
 */

public class FragmentFactory {
    public static final int REALTIME = 0;
    public static final int DATA = 1;
    public static final int FIND = 2;
    public static final int MINE = 3;
    public static final int WALLET = 4;


    public static Map<Integer, BaseFragment> fragmentMaps = new HashMap<>();


    public static BaseFragment createFragment(int position) {

        BaseFragment fragment = null;

        //缓存fragment,便于操作
        if (fragmentMaps.containsKey(position)) {
            return fragmentMaps.get(position);
        }
        switch (position) {
            case REALTIME:
                fragment = new HomeFragment();
                break;
            case DATA:
                fragment = new TouziFragment();
                break;
            case FIND:
                fragment = new C2cFragment();
                break;
            case MINE:
                fragment = new AppFragment();
                break;
            case WALLET:
                fragment = new WalletFragment();
                break;
            default:
                break;
        }
        fragmentMaps.put(position, fragment);
        return fragment;
    }

}
