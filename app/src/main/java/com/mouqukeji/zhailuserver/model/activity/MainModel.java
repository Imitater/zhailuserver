package com.mouqukeji.zhailuserver.model.activity;

import android.content.Context;

import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;

 import com.mouqukeji.zhailuserver.contract.activity.MainContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
 import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class MainModel implements MainContract.Model {

    @Override
    public Observable<BaseHttpResponse<LocationUpBean>> locationUp(String user_id, String lat, String lng) {
        return RetrofitManager.getInstance().getRequestService().locationUp(user_id,lat,lng);
    }
    @Override
    public Observable<BaseHttpResponse<InfoBean>> getInfo(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getInfo(user_id);
    }
    @Override
    public Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code,String type) {
        return RetrofitManager.getInstance().getRequestService().checkVersion(platform, version_code,type);
    }
}
