package com.mouqukeji.zhailuserver.model.fragment;


import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.contract.fragment.IncomeContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class IncomeModel implements IncomeContract.Model {

    @Override
    public Observable<BaseHttpResponse<PaymentDetailsBean>> getPaymentDetails(String user_id, String type) {
        return RetrofitManager.getInstance().getRequestService().getPaymentDetails(user_id,type);
    }
}
