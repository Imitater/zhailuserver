package com.mouqukeji.zhailuserver.presenter.activity;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.UserInfoUpBean;
import com.mouqukeji.zhailuserver.contract.activity.InfomationContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class InfomationPresenter extends InfomationContract.Presenter {
    @Override
    public void getInfo(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getInfo(user_id), new RxObserverListener<InfoBean>(mView) {
            @Override
            public void onSuccess(InfoBean result) {
                mView.getInfo(result);
            }
        }));
    }

    @Override
    public void putUserInfo(String user_id, String nickname, String gender, String school_name, String avatar, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.putUserInfo(user_id,nickname,gender,school_name,avatar), new RxObserverListener<UserInfoUpBean>(mView) {
            @Override
            public void onSuccess(UserInfoUpBean result) {
                mView.putUserInfo(result);
            }
        }));
    }
}
