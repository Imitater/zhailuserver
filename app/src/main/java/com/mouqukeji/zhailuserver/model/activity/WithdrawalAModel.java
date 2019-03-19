package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.DeleteAccountBean;
import com.mouqukeji.zhailuserver.contract.activity.WithdrawalAContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class WithdrawalAModel implements WithdrawalAContract.Model {


    @Override
    public Observable<BaseHttpResponse<AccountListBean>> getAcountList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getAcountList(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<DeleteAccountBean>> deleteAccount(String account_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().deleteAccount(account_id,user_id);
    }
}
