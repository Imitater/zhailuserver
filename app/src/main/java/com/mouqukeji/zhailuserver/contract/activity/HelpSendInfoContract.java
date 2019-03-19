package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;

import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.SendOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface HelpSendInfoContract {
    interface View extends IBaseView {
        void sendOrderInfo(SendOrderInfoBean bean);

        void confirmPurchase(ConfirmGetBuyBean bean);

        void confirmService(ConfirmServiceBean bean);

        void confirmFinish(ConfirmFinishBean bean);

        void sendOrder(SendOrderBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<SendOrderInfoBean>> sendOrderInfo(String order_id, String cate_id);

        Observable<BaseHttpResponse<ConfirmGetBuyBean>> confirmPurchase(String order_id, String user_id, String picture, String money);

        Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService(String order_id);

        Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish(String order_id);

        Observable<BaseHttpResponse<SendOrderBean>> sendOrder(String order_id, String user_id, String picture, String money);

    }

    abstract class Presenter extends BasePresenter<HelpSendInfoContract.View, HelpSendInfoContract.Model> {
        public abstract void sendfOrderInfo(String order_id, String cate_id, MultipleStatusView multipleStatusView);

        public abstract void confirmPurchase(String order_id, String user_id, String picture, String money, MultipleStatusView multipleStatusView);

        public abstract void confirmService(String order_id, MultipleStatusView multipleStatusView);

        public abstract void confirmFinish(String order_id, MultipleStatusView multipleStatusView);

        public abstract void sendOrder(String order_id, String user_id, String picture, String money, MultipleStatusView multipleStatusView);

    }
}
