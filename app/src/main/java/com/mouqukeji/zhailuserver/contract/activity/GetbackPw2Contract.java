package com.mouqukeji.zhailuserver.contract.activity;



import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.CodeCheckBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface GetbackPw2Contract {
    interface View extends IBaseView {
        void checkCode(CodeCheckBean bean);
        void getCode(CodeBean bean);
        void isSend();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeCheckBean>> checkCode(String number, String code);
        Observable<BaseHttpResponse<CodeBean>> getCode(String number, String type);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void checkCode(String number,String code, MultipleStatusView multipleStatusView);
        public abstract void getCode(String number,String type, MultipleStatusView multipleStatusView);
    }
}
