package com.mouqukeji.zhailuserver.contract.activity;


import android.app.Activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.EditPasswordBean;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;


public interface ChangePwContract {
    interface View extends IBaseView {
        void editPassword(EditPasswordBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<EditPasswordBean>> editPassword(String user_id,String password,String new_password);
     }

    abstract class Presenter extends BasePresenter<ChangePwContract.View, ChangePwContract.Model> {
        public abstract void editPassword(String telephone,String code,String password, MultipleStatusView multipleStatusView);
     }
}
