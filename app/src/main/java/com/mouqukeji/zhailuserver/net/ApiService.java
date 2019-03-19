package com.mouqukeji.zhailuserver.net;


import com.mouqukeji.zhailuserver.bean.AcceptOrderBean;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.AddAccountBean;
import com.mouqukeji.zhailuserver.bean.BalanceBean;
import com.mouqukeji.zhailuserver.bean.BuyOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.CashOutBean;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.CodeCheckBean;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.DeleteAccountBean;
import com.mouqukeji.zhailuserver.bean.DeliverOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.EditPasswordBean;
import com.mouqukeji.zhailuserver.bean.FeedBackBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.IdentityBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.SendOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.TakeOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.RegisteredBean;
import com.mouqukeji.zhailuserver.bean.SigninBean;
import com.mouqukeji.zhailuserver.bean.TokenBean;
import com.mouqukeji.zhailuserver.bean.UniversalOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.UserInfoUpBean;
import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.bean.YueWithdrawBean;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //登录
    @GET("deer/login/login")
    Observable<BaseHttpResponse<SigninBean>> login(@Query("telephone") String telephone, @Query("password") String password, @Query("did") String did);

    //获取验证码
    @GET("deer/Login/telCode")
    Observable<BaseHttpResponse<CodeBean>> getCode(@Query("telephone") String telephone, @Query("type") String type);

    //注册
    @GET("deer/Login/register")
    Observable<BaseHttpResponse<RegisteredBean>> registered(@Query("telephone") String telephone, @Query("code") String code, @Query("password") String password);

    //验证码核对
    @GET("deer/login/checkCode")
    Observable<BaseHttpResponse<CodeCheckBean>> checkCode(@Query("telephone") String telephone, @Query("code") String code);

    //重置密码
    @GET("deer/Login/reset")
    Observable<BaseHttpResponse<ResetPasswordBean>> resetPassword(@Query("telephone") String telephone, @Query("code") String code, @Query("password") String password);

    //token对比
    @GET("deer/Login/tokenCheck")
    Observable<BaseHttpResponse<TokenBean>> getToken(@Query("token") String token);

    //身份认证
    @GET("deer/v1/Identity/addIdentity")
    Observable<BaseHttpResponse<IdentityBean>> identityCertification(@Query("user_id") String user_id, @Query("name") String name, @Query("idcard") String idcard, @Query("school_name") String school_name);

    //个人信息
    @GET("deer/v1/User/personal")
    Observable<BaseHttpResponse<InfoBean>> getInfo(@Query("user_id") String user_id);

    //接单列表
    @GET("deer/v1/orders/receiptPage")
    Observable<BaseHttpResponse<GetOrderBean>> getOrder(@Query("user_id") String user_id, @Query("lat") String lat, @Query("lng") String lng, @Query("page") String page);

    //接单列表
    @GET("deer/v1/orders/receiptPage")
    Observable<BaseHttpResponse<GetOrderBean>> getOrderNext(@Query("user_id") String user_id, @Query("lat") String lat, @Query("lng") String lng, @Query("page") String page);

    //订单列表
    @GET("deer/v1/orders/ordersList")
    Observable<BaseHttpResponse<PutOrderBean>> putOrder(@Query("user_id") String user_id, @Query("progress") String progress, @Query("page") String page);

    //订单列表
    @GET("deer/v1/orders/ordersList")
    Observable<BaseHttpResponse<PutOrderBean>> putOrderNext(@Query("user_id") String user_id, @Query("progress") String progress, @Query("page") String page);

    //帮忙取订单详情
    @GET("deer/v1/orders/orderDetail")
    Observable<BaseHttpResponse<TakeOrderInfoBean>> orderInfo(@Query("order_id") String order_id, @Query("cate_id") String cate_id);

    //帮忙买订单详情
    @GET("deer/v1/orders/orderDetail")
    Observable<BaseHttpResponse<BuyOrderInfoBean>> buyOrderInfo(@Query("order_id") String order_id, @Query("cate_id") String cate_id);

    //帮忙送订单详情
    @GET("deer/v1/orders/orderDetail")
    Observable<BaseHttpResponse<DeliverOrderInfoBean>> deliverOrderInfo(@Query("order_id") String order_id, @Query("cate_id") String cate_id);

    //帮忙寄订单详情
    @GET("deer/v1/orders/orderDetail")
    Observable<BaseHttpResponse<SendOrderInfoBean>> sendOrderInfo(@Query("order_id") String order_id, @Query("cate_id") String cate_id);

    //钱包余额
    @GET("deer/v1/server_bill/balance")
    Observable<BaseHttpResponse<BalanceBean>> getBalance(@Query("user_id") String user_id);

    //收支明细
    @GET("deer/v1/server_bill/serverBillList")
    Observable<BaseHttpResponse<PaymentDetailsBean>> getPaymentDetails(@Query("user_id") String user_id, @Query("type") String type);

    //收入详情
    @GET("deer/v1/server_bill/serverBillDetail")
    Observable<BaseHttpResponse<WithdrawalBean>> getWithdrawal(@Query("bill_id") String bill_id);

    //提现详情
    @GET("deer/v1/server_bill/serverBillDetail")
    Observable<BaseHttpResponse<CashOutBean>> getCashOut(@Query("bill_id") String bill_id);

    //账号列表
    @GET("deer/v1/account/accountList")
    Observable<BaseHttpResponse<AccountListBean>> getAcountList(@Query("user_id") String user_id);

    //删除账号
    @GET("deer/v1/account/delaccount")
    Observable<BaseHttpResponse<DeleteAccountBean>> deleteAccount(@Query("account_id") String account_id, @Query("user_id") String user_id);

    //添加账号
    @GET("deer/v1/account/bingAccount")
    Observable<BaseHttpResponse<AddAccountBean>> addAccount(@Query("type") String type, @Query("user_id") String user_id, @Query("name") String name, @Query("account") String account);

    //提现申请
    @GET("deer/v1/Forward/forward")
    Observable<BaseHttpResponse<YueWithdrawBean>> yueWithdraw(@Query("user_id") String user_id, @Query("amount") String amount, @Query("account_id") String account_id);

    //接单
    @GET("deer/v1/orders/acceptOrders")
    Observable<BaseHttpResponse<AcceptOrderBean>> acceptOrder(@Query("order_id") String order_id, @Query("user_id") String user_id);

    //确认取货 购买
    @GET("deer/v1/orders/confirmPurchase")
    Observable<BaseHttpResponse<ConfirmGetBuyBean>> confirmPurchase(@Query("order_id") String order_id, @Query("user_id") String user_id,
                                                                    @Query("picture") String picture, @Query("money") String money);

    //确认配送
    @GET("deer/v1/orders/confirmService")
    Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService(@Query("order_id") String order_id);

    //二次支付完成
    @GET("deer/v1/orders/confirmFinish")
    Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish(@Query("order_id") String order_id);

    //万能帮订单详情
    @GET("deer/v1/orders/orderDetail")
    Observable<BaseHttpResponse<UniversalOrderInfoBean>> universalOrderInfo(@Query("order_id") String order_id, @Query("cate_id") String cate_id);

    //上传位置
    @GET("deer/v1/User/latLng")
    Observable<BaseHttpResponse<LocationUpBean>> locationUp(@Query("user_id") String user_id, @Query("lat") String lat, @Query("lng") String lng);

    //个人信息修改
    @GET("deer/v1/User/editUser")
    Observable<BaseHttpResponse<UserInfoUpBean>> putUserInfo(@Query("id") String user_id, @Query("nickname") String nickname, @Query("gender") String gender, @Query("school_name") String school_name, @Query("avatar") String avatar);

    //修改密码
    @GET("deer/v1/User/editPassword")
    Observable<BaseHttpResponse<EditPasswordBean>> editPassword(@Query("user_id") String user_id, @Query("password") String password, @Query("new_password") String new_password);

    //意见反馈
    @GET("deer/v1/feedback/feedbackAdd")
    Observable<BaseHttpResponse<FeedBackBean>> feedBack(@Query("picture") String picture, @Query("suggestion") String suggestion,
                                                        @Query("question") String question, @Query("user_id") String user_id);

    //版本更新
    @GET("deer/Login/checkVersion")
    Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(@Query("platform") String platform, @Query("version_code") String version_code, @Query("type") String type);

    //帮忙寄 确认寄出
    @GET("deer/v1/orders/sendOrder")
    Observable<BaseHttpResponse<SendOrderBean>> sendOrder(@Query("order_id") String order_id, @Query("user_id") String user_id, @Query("picture") String picture,@Query("money") String money);
}

