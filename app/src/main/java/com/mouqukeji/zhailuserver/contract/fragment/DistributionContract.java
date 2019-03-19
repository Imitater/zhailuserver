package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.SericeBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
 import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface DistributionContract {
    interface View extends IBaseView {
        void putOrder(PutOrderBean bean);
        void putOrderNext(PutOrderBean bean);
        void isEmpty();
        void confirmService(ConfirmServiceBean bean);
        void sendOrder(SendOrderBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PutOrderBean>> putOrder(String user_id, String progress, String page);
        Observable<BaseHttpResponse<PutOrderBean>> putOrderNext(String user_id, String progress, String page);
        Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService(String order_id);
        Observable<BaseHttpResponse<SendOrderBean>> sendOrder(String order_id, String user_id, String picture, String money);
    }

    abstract class Presenter extends BasePresenter<DistributionContract.View, DistributionContract.Model> {
        public abstract void putOrder(String user_id, String progress, String page, MultipleStatusView multipleStatusView);
        public abstract void putOrderNext(String user_id, String progress, String page, MultipleStatusView multipleStatusView);
        public abstract void confirmService(String order_id, MultipleStatusView multipleStatusView);
        public abstract void sendOrder(String order_id, String user_id, String picture, String money, MultipleStatusView multipleStatusView);
    }
}
