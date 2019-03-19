package com.mouqukeji.zhailuserver.model.activity;




import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw3Contract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw3Model implements GetbackPw3Contract.Model {

    @Override
    public Observable<BaseHttpResponse<ResetPasswordBean>> resetPassword(String number, String code, String password) {
        return RetrofitManager.getInstance().getRequestService().resetPassword(number,code,password);
    }
}
