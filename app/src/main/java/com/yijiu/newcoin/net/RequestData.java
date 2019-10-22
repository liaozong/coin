package com.yijiu.newcoin.net;



import com.yijiu.newcoin.factory.HashMapFactory;

import java.util.Map;

public class RequestData {

    public static Map getMap() {
        Map<String, String> map = HashMapFactory.getHashMap();
        return map;
    }

    public static Map sendCode(String mobile, String type) {
        Map<String, String> map = getMap();
        map.put("phoneNumber", mobile);
        map.put("type", type);
        return map;
    }
    public static Map login(String mobile, String password) {
        Map<String, Object> map = getMap();
        map.put("username", mobile);
        map.put("password", password);
//        map.put("countryId", code);
        return  map;
    }


    public static Map register(String verificationCode,  String password, String username,String paypwd,String invite_code) {
        Map<String, Object> map = getMap();
        map.put("mobile_code", verificationCode);
        map.put("paypwd", paypwd);
        map.put("password", password);
        map.put("username", username);
        map.put("invite_code", invite_code);
        return map;
    }
}
