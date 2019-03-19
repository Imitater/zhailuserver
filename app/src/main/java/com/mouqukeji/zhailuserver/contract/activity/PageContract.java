package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.BalanceBean;
import com.mouqukeji.zhailuserver.bean.DeliverOrderInfoBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;


public interface PageContract {
    interface View extends IBaseView {
        void getBalance(BalanceBean balanceBean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<BalanceBean>> getBalance(String user_id);
     }

    abstract class Presenter extends BasePresenter<PageContract.View, PageContract.Model> {
        public abstract void getBalance(String user_id, MultipleStatusView multipleStatusView);
     }
}
