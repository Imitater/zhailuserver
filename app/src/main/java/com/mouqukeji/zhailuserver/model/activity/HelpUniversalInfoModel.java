package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
 import com.mouqukeji.zhailuserver.bean.UniversalOrderInfoBean;
 import com.mouqukeji.zhailuserver.contract.activity.HelpUniversalInfoContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class HelpUniversalInfoModel implements HelpUniversalInfoContract.Model {

    @Override
    public Observable<BaseHttpResponse<UniversalOrderInfoBean>> universalOrderInfo(String order_id, String cate_id) {
        return RetrofitManager.getInstance().getRequestService().universalOrderInfo(order_id,cate_id);
    }
    @Override
    public Observable<BaseHttpResponse<ConfirmGetBuyBean>> confirmPurchase(String order_id, String user_id, String picture, String money) {
        return RetrofitManager.getInstance().getRequestService().confirmPurchase(order_id,user_id,picture,money);
    }

    @Override
    public Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService(String order_id) {
        return RetrofitManager.getInstance().getRequestService().confirmService(order_id);
    }

    @Override
    public Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish(String order_id) {
        return RetrofitManager.getInstance().getRequestService().confirmFinish(order_id);
    }
}
