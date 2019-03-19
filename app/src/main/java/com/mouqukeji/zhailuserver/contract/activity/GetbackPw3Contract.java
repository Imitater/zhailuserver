package com.mouqukeji.zhailuserver.contract.activity;
import android.app.Activity;
import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface GetbackPw3Contract {
    interface View extends IBaseView {
        void resetPassword(ResetPasswordBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ResetPasswordBean>> resetPassword(String number, String code, String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void resetPassword(Activity activity,String number,String code,String password, MultipleStatusView multipleStatusView);
    }
}
