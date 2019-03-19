package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.CashOutBean;
import com.mouqukeji.zhailuserver.contract.activity.CashOutContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class CashOutModel implements CashOutContract.Model {

    @Override
    public Observable<BaseHttpResponse<CashOutBean>> getCashOut(String bill_id) {
        return RetrofitManager.getInstance().getRequestService().getCashOut(bill_id);
    }
}
