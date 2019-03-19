package com.mouqukeji.zhailuserver.model.fragment;


import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.contract.fragment.WithdrawalContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class WithdrawalModel implements WithdrawalContract.Model {
    @Override
    public Observable<BaseHttpResponse<PaymentDetailsBean>> getPaymentDetails(String user_id, String type) {
        return RetrofitManager.getInstance().getRequestService().getPaymentDetails(user_id,type);
    }

}
