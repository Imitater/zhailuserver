package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.DeliverOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpDeliverInfoContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class HelpDeliverInfoPresenter extends  HelpDeliverInfoContract.Presenter{

    @Override
    public void deliverOrderInfo(String order_id, String cate_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.deliverOrderInfo(order_id,cate_id), new RxObserverListener<DeliverOrderInfoBean>(mView) {
            @Override
            public void onSuccess(DeliverOrderInfoBean result) {
                mView.deliverOrderInfo(result);
            }
        }));
    }
    @Override
    public void confirmPurchase(String order_id, String user_id, String picture, String money, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.confirmPurchase(order_id,user_id,picture,money), new RxObserverListener<ConfirmGetBuyBean>(mView) {
            @Override
            public void onSuccess(ConfirmGetBuyBean result) {
                mView.confirmPurchase(result);
            }
        }));
    }

    @Override
    public void confirmService(String order_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.confirmService(order_id), new RxObserverListener<ConfirmServiceBean>(mView) {
            @Override
            public void onSuccess(ConfirmServiceBean result) {
                mView.confirmService(result);
            }
        }));
    }
    public void confirmFinish(String order_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.confirmFinish(order_id), new RxObserverListener<ConfirmFinishBean>(mView) {
            @Override
            public void onSuccess(ConfirmFinishBean result) {
                mView.confirmFinish(result);
            }
        }));
    }

}

