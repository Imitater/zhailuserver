package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.contract.activity.PaymentInfoContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class PaymentInfoPresenter extends PaymentInfoContract.Presenter {

    @Override
    public void getWithdrawal(String bill_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getWithdrawal(bill_id), new RxObserverListener<WithdrawalBean>(mView) {
            @Override
            public void onSuccess(WithdrawalBean result) {
                mView.getWithdrawal(result);
            }
        }));
    }
}
