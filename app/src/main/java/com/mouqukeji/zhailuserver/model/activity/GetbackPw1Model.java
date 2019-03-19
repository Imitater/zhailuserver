package com.mouqukeji.zhailuserver.model.activity;




import com.mouqukeji.zhailuserver.bean.CodeBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw1Contract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw1Model implements GetbackPw1Contract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeBean>> getCode(String number, String code) {
        return RetrofitManager.getInstance().getRequestService().getCode(number,code);
    }
}
