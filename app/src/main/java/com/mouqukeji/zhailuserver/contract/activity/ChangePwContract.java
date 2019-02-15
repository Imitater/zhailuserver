package com.mouqukeji.zhailuserver.contract.activity;


import android.app.Activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;


public interface ChangePwContract {
    interface View extends IBaseView {
     }

    interface Model extends BaseModel {
     }

    abstract class Presenter extends BasePresenter<ChangePwContract.View, ChangePwContract.Model> {
     }
}
