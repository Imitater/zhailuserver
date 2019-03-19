package com.mouqukeji.zhailuserver.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.contract.fragment.IncomeContract;
import com.mouqukeji.zhailuserver.model.fragment.IncomeModel;
import com.mouqukeji.zhailuserver.presenter.fragment.IncomePresenter;
import com.mouqukeji.zhailuserver.ui.activity.PaymentInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.IncomeAdapter;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class IncomeFragment extends BaseFragment<IncomePresenter, IncomeModel> implements IncomeContract.View, View.OnClickListener {
    @BindView(R.id.income_recyclerview)
    RecyclerView incomeRecyclerview;
    @BindView(R.id.income_money_tv)
    TextView incomeMoneyTv;
    @BindView(R.id.income_swiperefreshlayout)
    SwipeRefreshLayout incomeSwiperefreshlayout;
      private IncomeAdapter incomeAdapter;
    private boolean flag = true;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getPaymentDetails(spUserID,"1",mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_income;
    }

    @Override
    protected void setUpView() {

    }


    private void initRecyclerview(List<PaymentDetailsBean.ServerBillListBean> serverBillList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        incomeRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        incomeAdapter = new IncomeAdapter(R.layout.adapter_payment_all, serverBillList);
        incomeRecyclerview.setAdapter(incomeAdapter);
        incomeAdapter.disableLoadMoreIfNotFullPage(incomeRecyclerview);
        incomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (incomeAdapter.getData().get(i).getType().equals("1")){
                    //订单跑腿收入
                    Intent intent = new Intent(getContext(), PaymentInfoActivity.class);
                    intent.putExtra("type",incomeAdapter.getData().get(i).getType());
                    intent.putExtra("bill_id",incomeAdapter.getData().get(i).getId());
                    getMContext().startActivity(intent);
                }else if (incomeAdapter.getData().get(i).getType().equals("2")) {
                    //二次支付收入
                    Intent intent = new Intent(getContext(), PaymentInfoActivity.class);
                    intent.putExtra("type", incomeAdapter.getData().get(i).getType());
                    intent.putExtra("bill_id", incomeAdapter.getData().get(i).getId());
                    getMContext().startActivity(intent);
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
        if (incomeSwiperefreshlayout!=null) {
            incomeSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            incomeSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    incomeRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.getPaymentDetails(spUserID,"1",mMultipleStateView);
                            if (incomeAdapter != null) {
                                incomeAdapter.notifyDataSetChanged();
                                incomeAdapter.setUpFetching(false);
                                incomeAdapter.setUpFetchEnable(false);
                            }
                            if (incomeSwiperefreshlayout!=null)
                            incomeSwiperefreshlayout.setRefreshing(false);
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
