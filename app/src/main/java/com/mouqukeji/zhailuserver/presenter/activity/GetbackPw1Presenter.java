package com.mouqukeji.zhailuserver.presenter.activity;

import android.app.Activity;

import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw1Contract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;


public class GetbackPw1Presenter extends  GetbackPw1Contract.Presenter{

    @Override
    public void getCode(final Activity activity, String number,String code ,final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number,code), new RxObserverListener<CodeBean>(mView) {
            @Override
            public void onSuccess(CodeBean result) {
                mView.getCode(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.isSend();
            }
        }));
    }
}

