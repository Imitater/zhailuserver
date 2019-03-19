package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.IdentityBean;
import com.mouqukeji.zhailuserver.contract.activity.IdentityContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class IdentityPresenter extends IdentityContract.Presenter {
    @Override
    public void identityCertification(String user_id, String name, String idcard, String school_name, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.identityCertification(user_id,name,idcard,school_name), new RxObserverListener<IdentityBean>(mView) {
            @Override
            public void onSuccess(IdentityBean result) {
                mView.identityCertification(result);
            }
        }));
    }
}
