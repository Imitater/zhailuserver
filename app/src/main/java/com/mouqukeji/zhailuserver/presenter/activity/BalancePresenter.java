package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.YueWithdrawBean;
import com.mouqukeji.zhailuserver.contract.activity.BalanceContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class BalancePresenter extends BalanceContract.Presenter {
    @Override
    public void getAcountList(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getAcountList(user_id), new RxObserverListener<AccountListBean>(mView) {
            @Override
            public void onSuccess(AccountListBean result) {
                mView.getAcountList(result);
            }

            @Override
            public void onBeing() {
                super.onBeing();
                mView.isEmpty();
            }
        }));
    }

    @Override
    public void yueWithdraw(String user_id, String amount, String account_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.yueWithdraw(user_id,amount,account_id), new RxObserverListener<YueWithdrawBean>(mView) {
            @Override
            public void onSuccess(YueWithdrawBean result) {
                mView.yueWithdraw(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.isEnoughMoney();
            }
        }));
    }
}
