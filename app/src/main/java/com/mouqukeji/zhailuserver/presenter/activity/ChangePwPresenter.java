package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.EditPasswordBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.contract.activity.ChangePwContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class ChangePwPresenter extends  ChangePwContract.Presenter{


    @Override
    public void editPassword(String telephone, String code, String password, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.editPassword(telephone,code,password), new RxObserverListener<EditPasswordBean>(mView) {
            @Override
            public void onSuccess(EditPasswordBean result) {
                mView.editPassword(result);
            }
        }));
    }
}

