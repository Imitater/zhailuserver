package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.FeedBackBean;
import com.mouqukeji.zhailuserver.contract.activity.AdviceContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class AdviceModel implements AdviceContract.Model {
    @Override
    public Observable<BaseHttpResponse<FeedBackBean>> feedBack(String picture, String suggestion, String question, String userid) {
        return RetrofitManager.getInstance().getRequestService().feedBack(picture,suggestion,question,userid);
    }
}
