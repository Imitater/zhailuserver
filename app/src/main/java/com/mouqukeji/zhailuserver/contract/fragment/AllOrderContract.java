package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
 import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface AllOrderContract {
    interface View extends IBaseView {
        void putOrder(PutOrderBean bean);
        void putOrderNext(PutOrderBean bean);
        void isEmpty();
        void confirmPurchase(ConfirmGetBuyBean bean);
        void confirmService(ConfirmServiceBean bean);
        void confirmFinish(ConfirmFinishBean bean);
        void sendOrder(SendOrderBean bean);

     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PutOrderBean>> putOrder(String user_id,String page,String progress);
        Observable<BaseHttpResponse<PutOrderBean>> putOrderNext(String user_id,String page,String progress);
        Observable<BaseHttpResponse<ConfirmGetBuyBean>> confirmPurchase( String order_id,String user_id,String picture,String money);
        Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService( String order_id);
        Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish( String order_id);
        Observable<BaseHttpResponse<SendOrderBean>> sendOrder(String order_id, String user_id, String picture, String money);


     }

    abstract class Presenter extends BasePresenter<AllOrderContract.View, AllOrderContract.Model> {
        public abstract void putOrder(String user_id,String progress,String page, MultipleStatusView multipleStatusView);
        public abstract void putOrderNext(String user_id,String progress,String page, MultipleStatusView multipleStatusView);
        public abstract void confirmPurchase(String order_id,String user_id,String picture,String money, MultipleStatusView multipleStatusView);
        public abstract void confirmService(String order_id,MultipleStatusView multipleStatusView);
        public abstract void confirmFinish(String order_id,MultipleStatusView multipleStatusView);

        public abstract void sendOrder(String order_id, String user_id, String picture, String money, MultipleStatusView multipleStatusView);
     }
}
