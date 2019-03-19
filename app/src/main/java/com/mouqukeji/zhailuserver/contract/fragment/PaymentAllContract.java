package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface PaymentAllContract {
    interface View extends IBaseView {
        void getPaymentDetails(PaymentDetailsBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PaymentDetailsBean>> getPaymentDetails(String user_id, String type);
     }

    abstract class Presenter extends BasePresenter<PaymentAllContract.View, PaymentAllContract.Model> {
        public abstract void getPaymentDetails(String user_id,String type, MultipleStatusView multipleStatusView);
     }
}
