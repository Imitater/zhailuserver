package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.BuyOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.DeleteAccountBean;
import com.mouqukeji.zhailuserver.contract.activity.WithdrawalAContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class WithdrawalAPresenter extends WithdrawalAContract.Presenter {

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
    public void deleteAccount(String account_id, String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.deleteAccount(account_id,user_id), new RxObserverListener<DeleteAccountBean>(mView) {
            @Override
            public void onSuccess(DeleteAccountBean result) {
                mView.deleteAccount(result);
            }
        }));
    }
}
