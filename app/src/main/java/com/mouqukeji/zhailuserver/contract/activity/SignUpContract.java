package com.mouqukeji.zhailuserver.contract.activity;


import android.app.Activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.RegisteredBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SignUpContract {
    interface View extends IBaseView {
        void getCode(CodeBean bean);
        void registered(RegisteredBean bean);
        void isRegistered();
        void isSend();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeBean>> getCode(String number, String type);
        Observable<BaseHttpResponse<RegisteredBean>> registered(String number, String code, String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getCode(Activity activity,String number,String type,MultipleStatusView multipleStatusView);
        public abstract void registered(String number,String code,String password,MultipleStatusView multipleStatusView);
    }
}
