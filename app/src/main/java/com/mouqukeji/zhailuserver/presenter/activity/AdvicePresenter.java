package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.FeedBackBean;
import com.mouqukeji.zhailuserver.contract.activity.AdviceContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class AdvicePresenter extends  AdviceContract.Presenter{

    @Override
    public void feedBack(String picture, String suggestion, String question, String userid, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.feedBack(picture,suggestion,question,userid), new RxObserverListener<FeedBackBean>(mView) {
            @Override
            public void onSuccess(FeedBackBean result) {
                mView.feedBack(result);
            }
        }));
    }
}

