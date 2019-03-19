package com.mouqukeji.zhailuserver.model.fragment;


import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.contract.fragment.ConfirmedContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class ConfirmedModel implements ConfirmedContract.Model {
    @Override
    public Observable<BaseHttpResponse<PutOrderBean>> putOrder(String user_id, String progress,String page) {
        return RetrofitManager.getInstance().getRequestService().putOrder(user_id,progress,page);
    }

    @Override
    public Observable<BaseHttpResponse<PutOrderBean>> putOrderNext(String user_id, String progress, String page) {
        return RetrofitManager.getInstance().getRequestService().putOrderNext(user_id,progress,page);
    }

    @Override
    public Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish(String order_id) {
        return RetrofitManager.getInstance().getRequestService().confirmFinish(order_id);
    }
}
