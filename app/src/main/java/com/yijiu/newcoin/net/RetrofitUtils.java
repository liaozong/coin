package com.yijiu.newcoin.net;


import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {
    private static Retrofit stringRetrofit;
    public static String language = "zh_CN";
    public static String SUCCESS = "success";
    public static final String ShareUrl = "http://39.98.191.42:8086/index.php/Member/Public/reg/mobile/";
    /*图片正式地址*/
    public static final String ImgUrl = "http://47.91.219.32:8882/";
    //    public static final String ImgUrl = "http://192.168.100.54:8882/";  /*图片测试地址*/
    /*正式地址*/
//    public static String Ip = "47.91.219.32:8881";
    /*测试地址*/
    public static String Ip = "http://192.168.100.199:8088";

    public static final MediaType OK_JSON = MediaType.parse("application/json; charset=utf-8");

    public static void setIp(String ip) {
        Ip = ip;
        requestSerives = null;
        stringRetrofit = null;
    }

    public static String getToken(String timestamp) {
//        return Utils.stringMD5(BaseApplication.getUser().getPhone() + timestamp + "app");
        return null;
    }

    public static Boolean isSuccess(Response<String> response) {
        if (response.code() == 200)
            return true;
        return false;
    }

    public static String baseUrl = "http://" + Ip + "/";

    public static Retrofit getStringRetrofit() {
        /*http://192.168.8.109:8081/appServer/sleepReport/test?user_id=4&usertets=5*/
        if (stringRetrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (stringRetrofit == null)
                    stringRetrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)//  请求地址
                            .client(getOKHttpClient())
                            //增加返回值为String的支持
                            .addConverterFactory(ScalarsConverterFactory.create())
                            //增加返回值为Gson的支持(以实体类返回)
//                        .addConverterFactory(GsonConverterFactory.create())
                            //增加返回值为Oservable<T>的支持
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
            }
        }
        return stringRetrofit;
    }

    private static RequestSerives requestSerives;

    public static RequestSerives getRequestServies() {
        //这里采用的是Java的动态代理模式
        if (requestSerives == null) {
            synchronized (RetrofitUtils.class) {
                if (requestSerives == null)
                    requestSerives = getStringRetrofit().create(RequestSerives.class);

            }
        }
        return requestSerives;
    }

    /*  private static Retrofit beanRetrofit;

      public static Retrofit getBeanRetrofit() {

          if (beanRetrofit == null) {
              synchronized (RetrofitUtils.class) {
                  if (beanRetrofit == null)
                      beanRetrofit = new Retrofit.Builder()
                              .baseUrl(HttpRestClient.BASE_URL)
                              .client(getOKHttpClient())
                              //增加返回值为String的支持
  //                            .addConverterFactory(ScalarsConverterFactory.create())
                              //增加返回值为Gson的支持(以实体类返回)
                              .addConverterFactory(GsonConverterFactory.create())
                              //增加返回值为Oservable<T>的支持
  //                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                              .build();
              }
          }
          return beanRetrofit;
      }
  */
    private static OkHttpClient okHttpClient;
    private final static int DEFAULT_TIME_OUT = 5;

    private static OkHttpClient getOKHttpClient() {

        if (okHttpClient == null) {
            synchronized (RetrofitUtils.class) {
                if (okHttpClient == null)
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接超时时间
                            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
                            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//读操作超时时间
                            .build();
            }
        }
        return okHttpClient;
    }

}