package com.mouqukeji.zhailuserver.presenter.activity;


import android.app.Activity;

import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.TokenBean;
import com.mouqukeji.zhailuserver.contract.activity.SplashContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;


public class SplashPresenter extends SplashContract.Presenter {
    @Override
    public void getToken(final Activity activity, String token, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getToken(token), new RxObserverListener<TokenBean>(mView) {
            @Override
            public void onSuccess(TokenBean result) {
                mView.getToken(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.restart();
            }
        }));
    }
}

