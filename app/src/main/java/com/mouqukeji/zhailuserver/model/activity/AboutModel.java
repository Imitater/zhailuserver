package com.mouqukeji.zhailuserver.model.activity;


import android.content.Context;


import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.contract.activity.AboutContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class AboutModel implements AboutContract.Model {
    @Override
    public Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code,String type) {
        return RetrofitManager.getInstance().getRequestService().checkVersion(platform, version_code,type);
    }
}
