package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.FeedBackBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface AdviceContract {
    interface View extends IBaseView {
        void feedBack(FeedBackBean backBean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<FeedBackBean>> feedBack(String picture, String suggestion, String question, String userid);
     }

    abstract class Presenter extends BasePresenter<AdviceContract.View, AdviceContract.Model> {
        public abstract void feedBack(String picture,String suggestion,String question,String userid,MultipleStatusView multipleStatusView);
      }
}
