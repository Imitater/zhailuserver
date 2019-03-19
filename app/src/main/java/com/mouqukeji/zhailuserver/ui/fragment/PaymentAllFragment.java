package com.mouqukeji.zhailuserver.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.contract.activity.CashOutContract;
import com.mouqukeji.zhailuserver.contract.fragment.PaymentAllContract;
import com.mouqukeji.zhailuserver.model.fragment.PaymentAllModel;
import com.mouqukeji.zhailuserver.presenter.fragment.PaymentAllPresenter;
import com.mouqukeji.zhailuserver.ui.activity.CashOutActivity;
import com.mouqukeji.zhailuserver.ui.activity.PaymentInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.PaymentAllAdapter;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class PaymentAllFragment extends BaseFragment<PaymentAllPresenter, PaymentAllModel> implements PaymentAllContract.View, View.OnClickListener {
    @BindView(R.id.paymentall_recyclerview)
    RecyclerView paymentallRecyclerview;
     @BindView(R.id.paymentall_swiperefreshlayout)
    SwipeRefreshLayout paymentallSwiperefreshlayout;
    private PaymentAllAdapter paymentAllAdapter;
    private String spUserID;
    private boolean flag = true;
    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getPaymentDetails(spUserID,"0",mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_paymentall;
    }

    @Override
    protected void setUpView() {

    }



    private void initRecyclerview(List<PaymentDetailsBean.ServerBillListBean> serverBillList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        paymentallRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        paymentAllAdapter = new PaymentAllAdapter(R.layout.adapter_payment_all, serverBillList);
        paymentallRecyclerview.setAdapter(paymentAllAdapter);
        paymentAllAdapter.disableLoadMoreIfNotFullPage(paymentallRecyclerview);
        //item点击事件
        paymentAllAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (paymentAllAdapter.getData().get(i).getType().equals("1")){
                    //订单跑腿收入
                    Intent intent = new Intent(getContext(), PaymentInfoActivity.class);
                    intent.putExtra("type",paymentAllAdapter.getData().get(i).getType());
                    intent.putExtra("bill_id",paymentAllAdapter.getData().get(i).getId());
                    getMContext().startActivity(intent);
                }else if (paymentAllAdapter.getData().get(i).getType().equals("2")){
                    //二次支付收入
                    Intent intent = new Intent(getContext(), PaymentInfoActivity.class);
                    intent.putExtra("type",paymentAllAdapter.getData().get(i).getType());
                    intent.putExtra("bill_id",paymentAllAdapter.getData().get(i).getId());
                    getMContext().startActivity(intent);
                }else{
                    //提现
                    Intent intent = new Intent(getMContext(), CashOutActivity.class);
                    intent.putExtra("bill_id",paymentAllAdapter.getData().get(i).getId());
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        if (paymentallSwiperefreshlayout!=null) {
            paymentallSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            paymentallSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    paymentallRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.getPaymentDetails(spUserID,"0",mMultipleStateView);
                            if (paymentAllAdapter != null) {
                                paymentAllAdapter.notifyDataSetChanged();
                                paymentAllAdapter.setUpFetching(false);
                                paymentAllAdapter.setUpFetchEnable(false);
                            }
                            if (paymentallSwiperefreshlayout!=null)
                            paymentallSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }



    @Override
    public void getPaymentDetails(PaymentDetailsBean bean) {
        if (flag) {
            flag = false;
            //设置recyclerview
            initRecyclerview(bean.getServerBillList());
        }
         //设置下拉刷新
        initSwipeRefresh();
    }
    @Override
    public void onStart() {
        super.onStart();
        flag = true;
    }
}
