package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.CashOutBean;
import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface CashOutContract {
    interface View extends IBaseView {
        void getCashOut(CashOutBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CashOutBean>> getCashOut(String bill_id);
     }

    abstract class Presenter extends BasePresenter<CashOutContract.View, CashOutContract.Model> {
        public abstract void getCashOut(String bill_id, MultipleStatusView multipleStatusView);
      }
}
