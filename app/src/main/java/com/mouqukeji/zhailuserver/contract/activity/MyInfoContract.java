package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface MyInfoContract {
    interface View extends IBaseView {
        void getInfo(InfoBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<InfoBean>> getInfo(String user_id);
     }

    abstract class Presenter extends BasePresenter<MyInfoContract.View, MyInfoContract.Model> {
        public abstract void getInfo(String user_id, MultipleStatusView multipleStatusView);
      }
}
