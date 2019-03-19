package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.IdentityBean;
import com.mouqukeji.zhailuserver.contract.activity.IdentityContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class IdentityModel implements IdentityContract.Model {


    @Override
    public Observable<BaseHttpResponse<IdentityBean>> identityCertification(String user_id, String name, String idcard, String school_name) {
        return RetrofitManager.getInstance().getRequestService().identityCertification(user_id,name,idcard,school_name);
    }
}
