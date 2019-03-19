package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.CashOutBean;
import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.contract.activity.CashOutContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class CashPresenter extends  CashOutContract.Presenter{

    @Override
    public void getCashOut(String bill_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCashOut(bill_id), new RxObserverListener<CashOutBean>(mView) {
            @Override
            public void onSuccess(CashOutBean result) {
                mView.getCashOut(result);
            }
        }));
    }
}

