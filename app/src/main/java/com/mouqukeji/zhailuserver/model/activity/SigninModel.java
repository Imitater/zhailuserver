package com.mouqukeji.zhailuserver.model.activity;




import com.mouqukeji.zhailuserver.bean.SigninBean;
import com.mouqukeji.zhailuserver.contract.activity.SignInContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class SigninModel implements SignInContract.Model {

    @Override
    public Observable<BaseHttpResponse<SigninBean>> signIn(String number, String password,String did) {
        return RetrofitManager.getInstance().getRequestService().login(number,password,did);
    }
}
