package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.CodeCheckBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw2Contract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class GetbackPw2Presenter extends  GetbackPw2Contract.Presenter{

    @Override
    public void checkCode(String number, String code, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.checkCode(number,code), new RxObserverListener<CodeCheckBean>(mView) {
            @Override
            public void onSuccess(CodeCheckBean result) {
                mView.checkCode(result);
            }
        }));
    }

    @Override
    public void getCode(String number, String type, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number,type), new RxObserverListener<CodeBean>(mView) {
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

