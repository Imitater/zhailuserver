package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.UserInfoUpBean;
import com.mouqukeji.zhailuserver.contract.activity.InfomationContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class InformationModel implements InfomationContract.Model {
    @Override
    public Observable<BaseHttpResponse<InfoBean>> getInfo(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getInfo(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<UserInfoUpBean>> putUserInfo(String user_id, String nickname, String gender, String school_name, String avatar) {
        return RetrofitManager.getInstance().getRequestService().putUserInfo(user_id,nickname,gender,school_name,avatar);
    }

}
