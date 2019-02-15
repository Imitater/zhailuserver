package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;

public interface HelpTakeInfoContract {
    interface View extends IBaseView {

     }

    interface Model extends BaseModel {

     }

    abstract class Presenter extends BasePresenter<HelpTakeInfoContract.View, HelpTakeInfoContract.Model> {
      }
}
