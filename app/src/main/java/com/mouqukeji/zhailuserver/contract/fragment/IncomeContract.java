package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;


public interface IncomeContract {
    interface View extends IBaseView {
     }

    interface Model extends BaseModel {
     }

    abstract class Presenter extends BasePresenter<IncomeContract.View, IncomeContract.Model> {
     }
}
