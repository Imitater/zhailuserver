package com.mouqukeji.zhailuserver.contract.activity;


import android.app.Activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.TokenBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SplashContract {

    interface View extends IBaseView {
        void getToken(TokenBean bean);
        void restart();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<TokenBean>> getToken(String token);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getToken(Activity activity,String token, MultipleStatusView multipleStatusView);
    }
}
