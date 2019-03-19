package com.mouqukeji.zhailuserver.presenter.fragment;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.AcceptOrderBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SericeBean;
import com.mouqukeji.zhailuserver.bean.TeriminalInfoBean;
import com.mouqukeji.zhailuserver.contract.fragment.ReceiptContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class ReceiptPresenter extends ReceiptContract.Presenter {
    @Override
    public void getOrder(String user_id, String lat, String lon, String page, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getOrder(user_id,lat,lon,page), new RxObserverListener<GetOrderBean>(mView) {
            @Override
            public void onSuccess(GetOrderBean result) {
                if (result.getOrdersList().size()!=0) {
                    mView.getOrder(result);
                }else{
                    mView.isEmpty();
                }
            }
        }));
    }

    @Override
    public void getOrderNext(String user_id, String lat, String lon, String page, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getOrderNext(user_id,lat,lon,page), new RxObserverListener<GetOrderBean>(mView) {
            @Override
            public void onSuccess(GetOrderBean result) {
                if (result.getPages()!=0) {
                    mView.getOrderNext(result);
                }else{
                    mView.isEmpty();
                }
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.isEmpty();
            }
        }));
    }

    @Override
    public void acceptOrder(String order_id, String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.acceptOrder(order_id,user_id), new RxObserverListener<AcceptOrderBean>(mView) {
            @Override
            public void onSuccess(AcceptOrderBean result) {
                mView.acceptOrder(result);
            }

            @Override
            public void onBeing() {
                super.onBeing();
                mView.rushOrder();
            }
        }));
    }

}
