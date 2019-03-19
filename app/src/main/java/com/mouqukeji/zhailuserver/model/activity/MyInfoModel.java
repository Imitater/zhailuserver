package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.contract.activity.MyInfoContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class MyInfoModel implements MyInfoContract.Model {
    @Override
    public Observable<BaseHttpResponse<InfoBean>> getInfo(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getInfo(user_id);
    }
}
