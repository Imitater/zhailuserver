package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.UserInfoUpBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface InfomationContract {
    interface View extends IBaseView {
        void getInfo(InfoBean bean);

        void putUserInfo(UserInfoUpBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<InfoBean>> getInfo(String user_id);

        Observable<BaseHttpResponse<UserInfoUpBean>> putUserInfo(String user_id,String nickname,String gender,String school_name,String avatar);
    }

    abstract class Presenter extends BasePresenter<InfomationContract.View, InfomationContract.Model> {
        public abstract void getInfo(String user_id, MultipleStatusView multipleStatusView);

        public abstract void putUserInfo(String user_id,String nickname,String gender,String school_name,String avatar, MultipleStatusView multipleStatusView);
    }
}
