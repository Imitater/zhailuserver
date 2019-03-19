package com.mouqukeji.zhailuserver.contract.activity;


import android.content.Context;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface AboutContract {
    interface View extends IBaseView {
        void checkVersion(CheckVersionBean backBean);
        void isNeedUp();
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code,String type);
     }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void checkVersion(Context context,String platform, String version_code,String type,MultipleStatusView multipleStatusView);
     }
}
