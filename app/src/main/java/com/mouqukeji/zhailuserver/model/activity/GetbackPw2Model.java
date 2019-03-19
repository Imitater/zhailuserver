package com.mouqukeji.zhailuserver.model.activity;




import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.bean.CodeCheckBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw2Contract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw2Model implements GetbackPw2Contract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeCheckBean>> checkCode(String number, String code) {
        return RetrofitManager.getInstance().getRequestService().checkCode(number,code);
    }

    @Override
    public Observable<BaseHttpResponse<CodeBean>> getCode(String number, String type) {
        return RetrofitManager.getInstance().getRequestService().getCode(number,type);
    }
}
