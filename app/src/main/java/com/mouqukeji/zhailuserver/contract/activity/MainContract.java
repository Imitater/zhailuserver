package com.mouqukeji.zhailuserver.contract.activity;


import android.content.Context;

import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface MainContract {
    interface View extends IBaseView {
        void locationUp(LocationUpBean bean);
        void getInfo(InfoBean bean);
        void checkVersion(CheckVersionBean backBean);
        void isNeedUp();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<LocationUpBean>> locationUp(String user_id, String lat, String lng);
        Observable<BaseHttpResponse<InfoBean>> getInfo(String user_id);
        Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code,String type);
    }


    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {
        public abstract void locationUp(String user_id, String lat, String lng, MultipleStatusView multipleStatusView);
        public abstract void getInfo(String user_id, MultipleStatusView multipleStatusView);
        public abstract void checkVersion(Context context, String platform, String version_code, String type,MultipleStatusView multipleStatusView);
    }
}
