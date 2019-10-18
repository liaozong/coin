package com.yijiu.newcoin.factory;


import com.yijiu.newcoin.base.BaseFragment;
import com.yijiu.newcoin.fragment.HomeFragment;
import com.yijiu.newcoin.fragment.MineFragment;
import com.yijiu.newcoin.fragment.ShoppingCarFragment;
import com.yijiu.newcoin.fragment.SortFragment;
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
                fragment = new SortFragment();
                break;
            case FIND:
                fragment = new ShoppingCarFragment();
                break;
            case MINE:
                fragment = new MineFragment();
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
