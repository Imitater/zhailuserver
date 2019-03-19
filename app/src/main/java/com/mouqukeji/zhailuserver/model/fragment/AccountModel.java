package com.mouqukeji.zhailuserver.model.fragment;


 import com.mouqukeji.zhailuserver.bean.AddAccountBean;
import com.mouqukeji.zhailuserver.contract.fragment.AccountContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class AccountModel implements AccountContract.Model {
    @Override
    public Observable<BaseHttpResponse<AddAccountBean>> addAccount(String type, String user_id, String name, String account) {
        return RetrofitManager.getInstance().getRequestService().addAccount(type,user_id,name,account);
    }
}
