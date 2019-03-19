package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.BuyOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.UniversalOrderInfoBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpBuyInfoContract;
import com.mouqukeji.zhailuserver.contract.activity.HelpUniversalInfoContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class HelpUniversalInfoPresenter extends  HelpUniversalInfoContract.Presenter{


    @Override
    public void universalOrderInfo(String order_id, String cate_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.universalOrderInfo(order_id,cate_id), new RxObserverListener<UniversalOrderInfoBean>(mView) {
            @Override
            public void onSuccess(UniversalOrderInfoBean result) {
                mView.universalOrderInfo(result);
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

    @Override
    public void confirmFinish(String order_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.confirmFinish(order_id), new RxObserverListener<ConfirmFinishBean>(mView) {
            @Override
            public void onSuccess(ConfirmFinishBean result) {
                mView.confirmFinish(result);
            }
        }));
    }
}

