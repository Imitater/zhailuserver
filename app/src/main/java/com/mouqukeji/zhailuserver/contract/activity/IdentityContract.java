package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.IdentityBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface IdentityContract {
    interface View extends IBaseView {
        void identityCertification(IdentityBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<IdentityBean>> identityCertification(String user_id, String name, String idcard, String school_name);
    }

    abstract class Presenter extends BasePresenter<IdentityContract.View, IdentityContract.Model> {
        public abstract void identityCertification(String user_id, String name, String idcard,String school_name,MultipleStatusView multipleStatusView);
    }
}
