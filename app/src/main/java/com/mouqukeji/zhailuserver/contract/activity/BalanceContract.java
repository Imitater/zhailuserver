package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.YueWithdrawBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface BalanceContract {
    interface View extends IBaseView {
        void getAcountList(AccountListBean bean);
        void isEmpty();
        void yueWithdraw(YueWithdrawBean bean);
        void isEnoughMoney();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AccountListBean>> getAcountList(String user_id);
        Observable<BaseHttpResponse<YueWithdrawBean>> yueWithdraw(String user_id,String amount,String account_id);
    }

    abstract class Presenter extends BasePresenter<BalanceContract.View, BalanceContract.Model> {
        public abstract void getAcountList(String user_id, MultipleStatusView multipleStatusView);
        public abstract void yueWithdraw(String user_id,String amount,String account_id, MultipleStatusView multipleStatusView);
    }
}
