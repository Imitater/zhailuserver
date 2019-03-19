package com.mouqukeji.zhailuserver.presenter.fragment;


import com.mouqukeji.zhailuserver.base.RxObserverListener;
import com.mouqukeji.zhailuserver.bean.AddAccountBean;
import com.mouqukeji.zhailuserver.contract.fragment.AccountContract;
import com.mouqukeji.zhailuserver.net.RetrofitManager;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public class AccountPresenter extends AccountContract.Presenter {

    @Override
    public void addAccount(String type, String user_id, String name, String account, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.addAccount(type,user_id,name,account), new RxObserverListener<AddAccountBean>(mView) {
            @Override
            public void onSuccess(AddAccountBean result) {
                mView.addAccount(result);
            }
        }));
    }
}
