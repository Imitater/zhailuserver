package com.mouqukeji.zhailuserver.presenter.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SericeBean;
import com.mouqukeji.zhailuserver.bean.TokenBean;
import com.mouqukeji.zhailuserver.contract.activity.MainContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.activity.SignInActivity;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;
import com.mouqukeji.zhailuserver.utils.PhoneUtils;

public class MainPresenter extends MainContract.Presenter {

    @Override
    public void locationUp(String user_id, String lat, String lng, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.locationUp(user_id, lat, lng), new RxObserverListener<LocationUpBean>(mView) {
            @Override
            public void onSuccess(LocationUpBean result) {
                mView.locationUp(result);
            }
        }));
    }

    @Override
    public void getInfo(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getInfo(user_id), new RxObserverListener<InfoBean>(mView) {
            @Override
            public void onSuccess(InfoBean result) {
                mView.getInfo(result);
            }
        }));
    }

    @Override
    public void checkVersion(final Context context, String platform, String version_code, String type, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.checkVersion(context,platform,version_code,type), new RxObserverListener<CheckVersionBean>(mView) {
            @Override
            public void onSuccess(CheckVersionBean result) {
                if (result.getVersionInfo()!=null||!result.getVersionInfo().getVersion_code().equals(PhoneUtils.getVersionName(context))) {
                    mView.checkVersion(result);
                }else{
                    mView.isNeedUp();
                }
            }
        }));
    }
}
