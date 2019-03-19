package com.mouqukeji.zhailuserver.model.activity;




import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.RegisteredBean;
import com.mouqukeji.zhailuserver.contract.activity.SignUpContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class SignUpModel implements SignUpContract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeBean>> getCode(String number, String type) {
        return RetrofitManager.getInstance().getRequestService().getCode(number,type);
    }

    @Override
    public Observable<BaseHttpResponse<RegisteredBean>> registered(String number, String code, String password) {
        return RetrofitManager.getInstance().getRequestService().registered(number,code,password);
    }
}
