package com.mouqukeji.zhailuserver.contract.fragment;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;


public interface DistributionContract {
    interface View extends IBaseView {
     }

    interface Model extends BaseModel {
     }

    abstract class Presenter extends BasePresenter<DistributionContract.View, DistributionContract.Model> {
     }
}
