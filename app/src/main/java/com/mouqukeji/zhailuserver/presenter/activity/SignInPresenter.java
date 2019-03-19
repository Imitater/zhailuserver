package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.SigninBean;
import com.mouqukeji.zhailuserver.contract.activity.SignInContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class SignInPresenter extends  SignInContract.Presenter{

    @Override
    public void signIn(String number, String password,String did, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.signIn(number,password,did), new RxObserverListener<SigninBean>(mView) {
            @Override
            public void onSuccess(SigninBean result) {
                    mView.signIn(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.error();
            }
        }));
    }
}

