package com.mouqukeji.zhailuserver.presenter.fragment;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.contract.fragment.PaymentAllContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class PaymentAllPresenter extends PaymentAllContract.Presenter {
    @Override
    public void getPaymentDetails(String user_id, String type, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getPaymentDetails(user_id,type), new RxObserverListener<PaymentDetailsBean>(mView) {
            @Override
            public void onSuccess(PaymentDetailsBean result) {
                mView.getPaymentDetails(result);
            }
        }));
    }
}
