package com.mouqukeji.zhailuserver.model.activity;



import com.mouqukeji.zhailuserver.bean.EditPasswordBean;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.contract.activity.ChangePwContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;


public class ChangePwModel implements ChangePwContract.Model {


    @Override
    public Observable<BaseHttpResponse<EditPasswordBean>> editPassword(String user_id, String password, String new_password) {
        return RetrofitManager.getInstance().getRequestService().editPassword(user_id,password,new_password);
    }
}
