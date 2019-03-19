package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.BalanceBean;
import com.mouqukeji.zhailuserver.contract.activity.PageContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class PackModel implements PageContract.Model {


    @Override
    public Observable<BaseHttpResponse<BalanceBean>> getBalance(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getBalance(user_id);
    }
}
