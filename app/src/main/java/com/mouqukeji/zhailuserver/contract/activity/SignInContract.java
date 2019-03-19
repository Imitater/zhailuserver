package com.mouqukeji.zhailuserver.contract.activity;



import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.SigninBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SignInContract {
    interface View extends IBaseView {
        void signIn(SigninBean bean);
        void error();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<SigninBean>> signIn(String number, String password,String did);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void signIn(String number,String password, String did,MultipleStatusView multipleStatusView);
    }
}
