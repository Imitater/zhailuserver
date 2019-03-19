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
import com.mouqukeji.zhailuserver.contract.fragment.WithdrawalContract;
import com.mouqukeji.zhailuserver.model.fragment.WithdrawalModel;
import com.mouqukeji.zhailuserver.presenter.fragment.WithdrawalPresenter;
import com.mouqukeji.zhailuserver.ui.activity.CashOutActivity;
import com.mouqukeji.zhailuserver.ui.activity.PaymentInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.WithdrawalAdapter;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WithdrawalFragment extends BaseFragment<WithdrawalPresenter, WithdrawalModel> implements WithdrawalContract.View, View.OnClickListener {
    @BindView(R.id.withdrawal_recyclerview)
    RecyclerView withdrawalRecyclerview;
    @BindView(R.id.withdrawal_swiperefreshlayout)
    SwipeRefreshLayout withdrawalSwiperefreshlayout;
    private WithdrawalAdapter withdrawalAdapter;
    private boolean flag = true;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getPaymentDetails(spUserID, "2", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_withdrawal;
    }

    @Override
    protected void setUpView() {


    }


    private void initRecyclerview(List<PaymentDetailsBean.ServerBillListBean> serverBillList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        withdrawalRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        withdrawalAdapter = new WithdrawalAdapter(R.layout.adapter_payment_all, serverBillList);
        withdrawalRecyclerview.setAdapter(withdrawalAdapter);
        withdrawalAdapter.disableLoadMoreIfNotFullPage(withdrawalRecyclerview);
        withdrawalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //提现
                Intent intent = new Intent(getMContext(), CashOutActivity.class);
                intent.putExtra("bill_id", withdrawalAdapter.getData().get(i).getId());
                getMContext().startActivity(intent);
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
        if (withdrawalSwiperefreshlayout != null) {
            withdrawalSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            withdrawalSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    withdrawalRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.getPaymentDetails(spUserID, "2", mMultipleStateView);
                            if (withdrawalAdapter != null) {
                                withdrawalAdapter.notifyDataSetChanged();
                                withdrawalAdapter.setUpFetching(false);
                                withdrawalAdapter.setUpFetchEnable(false);
                            }
                            if (withdrawalSwiperefreshlayout != null)
                                withdrawalSwiperefreshlayout.setRefreshing(false);
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
