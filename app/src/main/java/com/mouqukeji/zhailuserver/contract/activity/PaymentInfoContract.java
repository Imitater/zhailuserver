package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface PaymentInfoContract {
    interface View extends IBaseView {
        void getWithdrawal(WithdrawalBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<WithdrawalBean>> getWithdrawal(String bill_id);
     }

    abstract class Presenter extends BasePresenter<PaymentInfoContract.View, PaymentInfoContract.Model> {
        public abstract void getWithdrawal(String bill_id, MultipleStatusView multipleStatusView);
     }
}
