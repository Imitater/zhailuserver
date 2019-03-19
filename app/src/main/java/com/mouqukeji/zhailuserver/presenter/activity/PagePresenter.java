package com.mouqukeji.zhailuserver.presenter.activity;

import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.BalanceBean;
import com.mouqukeji.zhailuserver.bean.DeliverOrderInfoBean;
import com.mouqukeji.zhailuserver.contract.activity.PageContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class PagePresenter extends PageContract.Presenter {

    @Override
    public void getBalance(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getBalance(user_id), new RxObserverListener<BalanceBean>(mView) {
            @Override
            public void onSuccess(BalanceBean result) {
                mView.getBalance(result);
            }
        }));
    }
}

