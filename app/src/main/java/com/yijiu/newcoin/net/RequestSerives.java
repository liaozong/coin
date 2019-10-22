package com.yijiu.newcoin.net;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RequestSerives {

    @POST("api/Noauth/login")
    Call<String> login(
            @Body RequestBody info
    );

    //        @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("api/Noauth/register")
//    @FormUrlEncoded
    Call<String> register(@Body RequestBody body);
//    Call<String> registed(@FieldMap Map<String, String> map);

    /*刷新，重新获取用户信息*/
    @POST("api/User/myCenter")
    Call<String> userInfo(@Body RequestBody body);


    @POST("user/saveFeedback")
    @FormUrlEncoded
    Call<String> feedBack(
            @FieldMap Map<String, String> map
    );

    @POST("user/recharge/buyScore")
    Call<String> buyScore(
            @Header("token") String token,
            @Query("score") String score,
            @Query("payType") String payType,
            @Query("payAmount") String payAmount,
            @Query("payPassword") String payPassword
    );


    @POST("user/checkVerficyCode")
    @FormUrlEncoded
    Call<String> checkVerficyCode(
            @FieldMap Map<String, String> map
    );

    @GET("user/info/sendRegisterSms")
    Call<String> getVerifyCode(/*@Header("x-auth-token") String token, @Header("Language") String Language,*/
            @Query("phoneNumber") String phoneNumber, @Query("countryId") String countryId
    );

    /*推出登录*/
    @POST("user/info/logout")
    Call<String> logout(@Header("token") String token);




    @GET("index/getAllInfo")
    Call<String> getAllInfo(/*@Header("x-auth-token") String token, @Header("Language") String Language,*/
            @Query("marketId") String phoneNumber
    );

    /*重置登录密码*/
    @GET("user/info/sendResetPwdSms")
    Call<String> sendResetPwdSms(@Query("countryId") String countryId, @Query("phone") String phone, @Query("type") String type);


    //    POST /user/info/resetPwd
    @POST("user/info/resetPwd")
    Call<String> updatePwd(@Header("token") String token, @Body RequestBody info);

    /*POST /user/info/setPayPwd 设置、修改支付密码*/
    /*/user/info/setPayPwd*/
    @POST(" user/info/setPayPwd")
//    Call<String> setPayPwd(@Header("token") String token, @Body RequestBody info);
    Call<String> setPayPwd(@Header("token") String token, @Query("payPassword") String payPassword,
                           @Query("verifyCode") String verifyCode);

    @GET("area/list")
//    @FormUrlEncoded
    Call<String> getArea(/*@Header("x-auth-token") String token, @Header("Language") String Language,*/
            @Query("phoneNumber") String phoneNumber
//                          @FieldMap Map<String, String> map
    );

    @GET("product/byName")
    Call<String> searchByName(
            @Query("productName") String productName,
            @Query("marketId") String marketId
    );

    @GET("product/categoryList")
    Call<String> categoryList(
            @Query("phoneNumber") String phoneNumber
    );

    @GET("product/byCategoryId")
    Call<String> byCategoryId(
            @Query("categoryId") String categoryId,
            @Query("marketId") String marketId
    );

    @GET("product/toDayHotSell")
    Call<String> toDayHotSell(
            @Query("marketId") String marketId
    );

    @GET("product/toDayRecommend")
    Call<String> toDayRecommend(
            @Query("marketId") String marketId
    );
    /*GET */

    @GET("market/getByAreaName")
    Call<String> getByAreaName(@Header("token") String token, @Query("areaName") String areaName);


    @GET("market/list")
//    @FormUrlEncoded
    Call<String> market(/*@Header("x-auth-token") String token, @Header("Language") String Language,*/
            @Query("phoneNumber") String phoneNumber
//                          @FieldMap Map<String, String> map
    );

    @GET("user/info/countryPhoneList")
    Call<String> countryPhoneList(
            @Query("phoneNumber") String phoneNumber
    );



    @POST("user/info/resetPwd")
    Call<String> resetPwd(@Body RequestBody info);

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("user/changeUser")
    Call<ResponseBody> changeUser(@Header("token") String token, @Body RequestBody info);   // 请求体味RequestBody 类型

    /*获取用户地址集合*/
    @GET("user/info/getUserDeliveryAddrs")
    Call<String> getUserDeliveryAddrs(@Header("token") String token,
                                      @Query("a") String a);

    @GET("product/byId")
    Call<String> byId(@Header("token") String token,
                      @Query("productId") String productId);

    /*POST 设置用户默认收货地址*/
    @POST("user/info/setDefDelivery")
    Call<String> setDefDelivery(@Header("token") String token, @Query("deliveryId") String deliveryId);

    /*POST 添加用户收货信息*/
    @POST("user/info/addDelivery")
    Call<String> addDelivery(@Header("token") String token, @Body RequestBody info);

    /*修改用户收货信息*/
    @POST("user/info/updateDelivery")
    Call<String> updateDelivery(@Header("token") String token, @Body RequestBody info);

    @Multipart
    @POST("user/updateAvatar.do")
    Call<Response> updateAvatar(@Query("des") String description, @Part("uploadFile\"; filename=\"test.jpg\"") RequestBody imgs);

    @Multipart
    @POST("user/info/upZfbQRCode")
    Call<String> upZfbQRCode(@Header("token") String token, @Query("codeType") String codeType, @Query("realName") String realName, @Query("account") String account, @Part("multipartFile\"; filename=\"test.png\"") RequestBody imgs);
//    Call<String> upZfbQRCode(@Header("token") String token, @Query("codeType") String codeType, @Part("file\"; filename=\"test.png\"") RequestBody imgs);

//    @Multipart
//    @POST("user/info/upZfbQRCode")
//    Call<String> upZfbQRCode(@Header("token") String token, @Query("codeType") String codeType, @Part MultipartBody.Part part);

    /*/user/cart/addCart*/
    @POST("user/cart/addCart")
    Call<String> addCart(@Header("token") String token, @Query("productId") String productId, @Query("count") String count);

    @POST("user/cart/settleAccounts")
    Call<String> settleAccounts(@Header("token") String token, @Query("marketId") String marketId, @Query("deliveryType") String deliveryType, @Query("note") String note);

    @POST(" user/cart/settleSomeProduct")
    Call<String> settleSomeProduct(@Header("token") String token, @Query("marketId") String marketId, @Query("productIds") String productIds, @Query("deliveryType") String deliveryType, @Query("note") String note);

    @POST("user/cart/editCart")
    Call<String> editCart(@Header("token") String token, @Query("productId") String productId, @Query("count") String count);

    @GET("user/cart/getUserCart")
    Call<String> getUserCart(@Header("token") String token);

    @POST("user/cart/deleteProduct")
    Call<String> deleteProduct(@Header("token") String token, @Query("productIds") String productIds);

    /*结算某个超市的购物车,将会清空购物车,生成一笔订单*/
//    @POST("user/cart/settleAccounts")
//    Call<String>settleAccounts(@Header("token") String token);
    /*开启或关闭是否自动匹配购买积分*/
    @POST("user/space/setAutoBuy")
    Call<String> setAutoBuy(@Header("token") String token, @Query("autoBuy") String autoBuy);


    /*开启或关闭是否自动出售积分*/
    @POST("user/space/setAutoSell")
    Call<String> setAutoSell(@Header("token") String token, @Query("autoSell") String autoSell);

    /*获取用户会员空间基本信息*/
    @GET("user/space/findUserSpaceProcess")
//user/space/findUserSpaceProcess  user/space/spaceInfo
    Call<String> spaceInfo(@Header("token") String token);

    /*获取积分卷充值设置*/
    @GET("user/recharge/countryPhoneList")
    Call<String> rechargecountryPhoneList(@Header("token") String token);

    /*GET 查询历史消费记录*/
    @GET("user/recharge/consumeHistory")
    Call<String> consumeHistory(@Header("token") String token, @Query("payType") String payType);

    /*GET 查询充值积分历史记录*/
    @GET("user/recharge/rechargeScoreHistory")
    Call<String> rechargeScoreHistory(@Header("token") String token);

    /*GET 查询充值USDT历史记录*/
    @GET("user/recharge/rechargeUsdtHistory")
    Call<String> rechargeUsdtHistory(@Header("token") String token);

    /*GET 查询提现USDT历史记录*/
    @GET("user/recharge/withdrawUsdtHistory")
    Call<String> withdrawUsdtHistory(@Header("token") String token);

    /*订单确认收货*/
    @POST("user/order/confirmOrder")
    Call<String> confirmOrder(@Header("token") String token, @Query("orderId") String orderId);

    /*获取用户所有订单及详情*/
    @GET("user/order/getUserOrder")
    Call<String> getUserOrder(@Header("token") String token, @Query("status") int status);

    /*支付一个订单*/
    @POST("user/order/payOrder")
    Call<String> payOrder(@Header("token") String token, @Query("orderId") String orderId, @Query("payPassword") String payPassword,
                          @Query("payType") String payType);

    /*删除一条用户收货信息*/
    @POST("user/info/deleteDelivery")
    Call<String> deleteDelivery(@Header("token") String token, @Query("deliveryId") String deliveryId);

    /*验证token  是否失效*/
    @GET("index/verifyToken")
    Call<String> verifyToken(@Header("token") String token);

    /*根据收货信息id查询收货地址详情*/
    @POST("user/info/getUserDeliveryById")
    Call<String> getUserDeliveryById(@Header("token") String token, @Query("deliveryId") String deliveryId);

    /*删除一个订单*/
    @POST("user/order/deleteOrder")
    Call<String> deleteOrder(@Header("token") String token, @Query("orderId") String orderId);

    /*绑定邀请码*/
    @POST("user/space/bindinvitationCode")
    Call<String> bindinvitationCode(@Header("token") String token, @Query("invitationCode") String invitationCode);

    /*取消接单*/
    @POST("user/space/cancleOrder")
    Call<String> cancleOrder(@Header("token") String token);

    /*接单*/
    @POST("user/space/checkOrder")
    Call<String> checkOrder(@Header("token") String token);

    /*绑定usdt地址*/
    @POST("user/info/bindUsdtAddr")
    Call<String> bindUsdtAddr(@Header("token") String token);
    /*GET /user/space/getPayWays*/

    /*确认付款*/
    @POST("user/space/confirmPay")
    Call<String> confirmPay(@Header("token") String token);

    /*确认收款*/
    @POST("user/space/confirmationOfReceipts")
    Call<String> confirmationOfReceipts(@Header("token") String token);

    /*会员空间模块的接口*/
    @GET("user/space/findUserSpaceProcessRequest")
    Call<String> findUserSpaceProcess(@Header("token") String token);

    /*会员升级*/
    @POST("user/space/upLevel")
    Call<String> upLevel(@Header("token") String token);

    /*开启或者关闭（支付宝、微信、USDT）支付*/
    @POST("user/space/operationPayStatus")
    Call<String> operationPayStatus(@Header("token") String token, @Query("payType") String payType, @Query("status") String status);

    /*USDT支付、微信支付、支付宝支付。USDT支付需要输入支付密码，其余两种属于线下支付---------------------------*/
    @POST("user/space/confirmPay")
    Call<String> confirmPayUserSpace(@Header("token") String token, @Body RequestBody requestBody);

    //    买家选择付款方式后查询此种支付方式的付款金额、付款地址、订单剩余时间等信息
    @POST("user/space/getPayMoney")
    Call<String> getPayMoney(@Header("token") String token, @Query("scoreOrderId") String scoreOrderId, @Query("payType") String payType);

    /*USDT提现，允许用户将账户余额提现到指定地址值，但每次要扣5个USDT作为手续费*/
    @POST("user/info/usdtWithdraw")
    Call<String> usdtWithdraw(@Header("token") String token, @Query("amount") String amount, @Query("address") String address, @Query("remark") String remark, @Query("payPassword") String payPassword);


    /*会员升级*/
    @POST("user/space/getScoreOrders")
    Call<String> getScoreOrders(@Header("token") String token);

    /*GET /user/space/getPayWays*/

    /*查询卖家的收款方式 */
    @GET("user/space/getPayWays")
    Call<String> getPayWays(@Header("token") String token, @Query("sellerId") String sellerId);

    /*user/space/getScoreOrders*/

    /*买家查询新的升级记录*/
    @GET("user/space/upLevelRecords")
    Call<String> upLevelRecords(@Header("token") String token);

    /*将用户查看过的升级记录数据状态改为已推送状态*/
    @GET("user/space/haveBeenPush")
    Call<String> haveBeenPush(@Header("token") String token);
}