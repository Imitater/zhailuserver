package com.mouqukeji.zhailuserver.presenter.fragment;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.contract.fragment.ConfirmedContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class ConfirmedPresenter extends ConfirmedContract.Presenter {
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

    public void confirmFinish(String order_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.confirmFinish(order_id), new RxObserverListener<ConfirmFinishBean>(mView) {
            @Override
            public void onSuccess(ConfirmFinishBean result) {
                mView.confirmFinish(result);
            }
        }));
    }
}
