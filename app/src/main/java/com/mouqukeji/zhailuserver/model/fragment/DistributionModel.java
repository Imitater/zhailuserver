package com.mouqukeji.zhailuserver.model.fragment;


import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.SericeBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.contract.fragment.DistributionContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
 import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class DistributionModel implements DistributionContract.Model {

    @Override
    public Observable<BaseHttpResponse<PutOrderBean>> putOrder(String user_id, String progress,String page) {
        return RetrofitManager.getInstance().getRequestService().putOrder(user_id,progress,page);
    }

    @Override
    public Observable<BaseHttpResponse<PutOrderBean>> putOrderNext(String user_id, String progress,String page) {
        return RetrofitManager.getInstance().getRequestService().putOrderNext(user_id,progress,page);
    }
    @Override
    public Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService(String order_id) {
        return RetrofitManager.getInstance().getRequestService().confirmService(order_id);
    }
    @Override
    public Observable<BaseHttpResponse<SendOrderBean>> sendOrder(String order_id, String user_id, String picture, String money) {
        return RetrofitManager.getInstance().getRequestService().sendOrder(order_id,user_id,picture,money);
    }
 }
