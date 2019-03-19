package com.mouqukeji.zhailuserver.presenter.fragment;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.contract.fragment.AllOrderContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class AllOrderPresenter extends AllOrderContract.Presenter {
    @Override
    public void putOrder(String user_id, String progress,String page, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.putOrder(user_id,progress,page), new RxObserverListener<PutOrderBean>(mView) {
            @Override
            public void onSuccess(PutOrderBean result) {
                if (result.getOrdersList().size()!=0) {
                    mView.putOrder(result);
                }else{
                    mView.isEmpty();
                }
            }


        }));
    }

    @Override
    public void putOrderNext(String user_id, String progress, String page, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.putOrderNext(user_id,progress,page), new RxObserverListener<PutOrderBean>(mView) {
            @Override
            public void onSuccess(PutOrderBean result) {
                mView.putOrderNext(result);
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

    @Override
    public void sendOrder(String order_id, String user_id, String picture, String money, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.sendOrder(order_id,user_id,picture,money), new RxObserverListener<SendOrderBean>(mView) {
            @Override
            public void onSuccess(SendOrderBean result) {
                mView.sendOrder(result);
            }
        }));
    }
}
