package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface ConfirmedContract {
    interface View extends IBaseView {
        void putOrder(PutOrderBean bean);
        void putOrderNext(PutOrderBean bean);
        void isEmpty();
        void confirmFinish(ConfirmFinishBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PutOrderBean>> putOrder(String user_id, String progress,String page);
        Observable<BaseHttpResponse<PutOrderBean>> putOrderNext(String user_id, String progress,String page);
        Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish( String order_id);
     }

    abstract class Presenter extends BasePresenter<ConfirmedContract.View,ConfirmedContract.Model> {
        public abstract void putOrder(String user_id, String progress,String page, MultipleStatusView multipleStatusView);
        public abstract void putOrderNext(String user_id, String progress,String page, MultipleStatusView multipleStatusView);
        public abstract void confirmFinish(String order_id,MultipleStatusView multipleStatusView);
     }
}
