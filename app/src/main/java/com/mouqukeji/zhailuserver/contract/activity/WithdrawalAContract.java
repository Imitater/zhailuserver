package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.BuyOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.DeleteAccountBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface WithdrawalAContract {
    interface View extends IBaseView {
        void getAcountList(AccountListBean bean);
        void deleteAccount(DeleteAccountBean bean);
        void isEmpty();
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AccountListBean>> getAcountList(String user_id);
        Observable<BaseHttpResponse<DeleteAccountBean>> deleteAccount(String account_id,String user_id);
     }

    abstract class Presenter extends BasePresenter<WithdrawalAContract.View, WithdrawalAContract.Model> {
        public abstract void getAcountList(String user_id, MultipleStatusView multipleStatusView);
        public abstract void deleteAccount(String account_id,String user_id, MultipleStatusView multipleStatusView);
     }
}
