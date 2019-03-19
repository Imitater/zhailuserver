package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.contract.activity.MyInfoContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class MyInfoPresenter extends  MyInfoContract.Presenter{
    @Override
    public void getInfo(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getInfo(user_id), new RxObserverListener<InfoBean>(mView) {
            @Override
            public void onSuccess(InfoBean result) {
                mView.getInfo(result);
            }
        }));
    }
}

