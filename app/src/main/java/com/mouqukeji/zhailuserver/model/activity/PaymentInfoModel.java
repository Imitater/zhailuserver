package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.contract.activity.PaymentInfoContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class PaymentInfoModel implements PaymentInfoContract.Model {
    @Override
    public Observable<BaseHttpResponse<WithdrawalBean>> getWithdrawal(String bill_id) {
        return RetrofitManager.getInstance().getRequestService().getWithdrawal(bill_id);
    }
}
