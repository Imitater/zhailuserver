package com.mouqukeji.zhailuserver.model.fragment;


import com.mouqukeji.zhailuserver.bean.AcceptOrderBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.contract.fragment.ReceiptContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
 import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class ReceiptModel implements ReceiptContract.Model {

    @Override
    public Observable<BaseHttpResponse<GetOrderBean>> getOrder(String user_id, String lat, String lon, String page) {
        return RetrofitManager.getInstance().getRequestService().getOrder(user_id,lat,lon,page);
    }

    @Override
    public Observable<BaseHttpResponse<GetOrderBean>> getOrderNext(String user_id, String lat, String lon, String page) {
        return RetrofitManager.getInstance().getRequestService().getOrderNext(user_id,lat,lon,page);
    }

    @Override
    public Observable<BaseHttpResponse<AcceptOrderBean>> acceptOrder(String order_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().acceptOrder(order_id,user_id);
    }

}
