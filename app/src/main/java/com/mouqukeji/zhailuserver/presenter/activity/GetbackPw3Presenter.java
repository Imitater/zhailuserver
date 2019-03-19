package com.mouqukeji.zhailuserver.presenter.activity;


import android.app.Activity;
import android.widget.Toast;

import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.ErrorBean;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw3Contract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class GetbackPw3Presenter extends  GetbackPw3Contract.Presenter{

    @Override
    public void resetPassword(final Activity activity, String number, String code, String password, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.resetPassword(number,code,password), new RxObserverListener<ResetPasswordBean>(mView) {
            @Override
            public void onSuccess(ResetPasswordBean result) {
                mView.resetPassword(result);
            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {
                Toast.makeText(activity, "密码重置错误", Toast.LENGTH_SHORT).show();
                super.onBusinessError(errorBean);
            }

        }));
    }
}

