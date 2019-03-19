package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.AcceptOrderBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface ReceiptContract {
    interface View extends IBaseView {
        void getOrder(GetOrderBean bean);

        void getOrderNext(GetOrderBean bean);

        void isEmpty();

        void acceptOrder(AcceptOrderBean bean);

        void rushOrder();


    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<GetOrderBean>> getOrder(String user_id, String lat, String lon, String page);

        Observable<BaseHttpResponse<GetOrderBean>> getOrderNext(String user_id, String lat, String lon, String page);

        Observable<BaseHttpResponse<AcceptOrderBean>> acceptOrder(String order_id, String user_id);

    }

    abstract class Presenter extends BasePresenter<ReceiptContract.View, ReceiptContract.Model> {
        public abstract void getOrder(String user_id, String lat, String lon, String page, MultipleStatusView multipleStatusView);

        public abstract void getOrderNext(String user_id, String lat, String lon, String page, MultipleStatusView multipleStatusView);

        public abstract void acceptOrder(String order_id, String user_id, MultipleStatusView multipleStatusView);


    }
}
