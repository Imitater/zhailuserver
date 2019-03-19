package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.AddAccountBean;
import com.mouqukeji.zhailuserver.bean.DeleteAccountBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;


public interface AccountContract {
    interface View extends IBaseView {
        void addAccount(AddAccountBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AddAccountBean>> addAccount(String type, String user_id, String name,String account);
    }

    abstract class Presenter extends BasePresenter<AccountContract.View, AccountContract.Model> {
        public abstract void addAccount(String type, String user_id, String name,String account,MultipleStatusView multipleStatusView);
    }
}
