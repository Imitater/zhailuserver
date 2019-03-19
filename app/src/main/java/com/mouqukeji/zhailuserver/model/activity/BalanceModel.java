package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.YueWithdrawBean;
import com.mouqukeji.zhailuserver.contract.activity.BalanceContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class BalanceModel implements BalanceContract.Model {

    @Override
    public Observable<BaseHttpResponse<AccountListBean>> getAcountList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getAcountList(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<YueWithdrawBean>> yueWithdraw(String user_id, String amount, String account_id) {
        return RetrofitManager.getInstance().getRequestService().yueWithdraw(user_id,amount,account_id);
    }
}
