package com.mouqukeji.zhailuserver.presenter.activity;


import android.content.Context;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.contract.activity.AboutContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;
import com.mouqukeji.zhailuserver.utils.PhoneUtils;

public class AboutPresenter extends  AboutContract.Presenter{


    @Override
    public void checkVersion(final Context context, String platform, String version_code, String type,MultipleStatusView multipleStatusView) {
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

