package com.mouqukeji.zhailuserver.contract.activity;


import com.mouqukeji.zhailuserver.base.BaseModel;
import com.mouqukeji.zhailuserver.base.BasePresenter;
import com.mouqukeji.zhailuserver.base.IBaseView;
import com.mouqukeji.zhailuserver.bean.BuyOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
 import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface HelpBuyInfoContract {
    interface View extends IBaseView {
        void buyOrderInfo(BuyOrderInfoBean bean);
        void confirmPurchase(ConfirmGetBuyBean bean);
        void confirmService(ConfirmServiceBean bean);
        void confirmFinish(ConfirmFinishBean bean);

     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<BuyOrderInfoBean>> buyOrderInfo(String order_id,String cate_id);
        Observable<BaseHttpResponse<ConfirmGetBuyBean>> confirmPurchase( String order_id,String user_id,String picture,String money);
        Observable<BaseHttpResponse<ConfirmServiceBean>> confirmService( String order_id);
        Observable<BaseHttpResponse<ConfirmFinishBean>> confirmFinish( String order_id);

     }

    abstract class Presenter extends BasePresenter<HelpBuyInfoContract.View, HelpBuyInfoContract.Model> {
        public abstract void buyOrderInfo(String order_id,String cate_id, MultipleStatusView multipleStatusView);
        public abstract void confirmPurchase(String order_id,String user_id,String picture,String money, MultipleStatusView multipleStatusView);
        public abstract void confirmService(String order_id,MultipleStatusView multipleStatusView);
        public abstract void confirmFinish(String order_id,MultipleStatusView multipleStatusView);

      }
}
