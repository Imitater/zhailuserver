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
import com.mouqukeji.zhailuserver.contract.fragment.WithdrawalContract;
import com.mouqukeji.zhailuserver.model.fragment.WithdrawalModel;
import com.mouqukeji.zhailuserver.presenter.fragment.WithdrawalPresenter;
import com.mouqukeji.zhailuserver.ui.activity.PaymentInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.WithdrawalAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WithdrawalFragment extends BaseFragment<WithdrawalPresenter, WithdrawalModel> implements WithdrawalContract.View, View.OnClickListener {
    @BindView(R.id.withdrawal_recyclerview)
    RecyclerView withdrawalRecyclerview;
    @BindView(R.id.withdrawal_swiperefreshlayout)
    SwipeRefreshLayout withdrawalSwiperefreshlayout;
    List list = new ArrayList();
    private int page=1;
    private WithdrawalAdapter withdrawalAdapter;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_withdrawal;
    }

    @Override
    protected void setUpView() {
        //假数据
        initData();
        initRecyclerview();
        //设置上拉加载
        setUpLoad();
        //设置下拉刷新
        initSwipeRefresh();
    }
    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        withdrawalRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        withdrawalAdapter = new WithdrawalAdapter(R.layout.adapter_payment_all, list);
        withdrawalRecyclerview.setAdapter(withdrawalAdapter);
        withdrawalAdapter.disableLoadMoreIfNotFullPage(withdrawalRecyclerview);
        withdrawalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getContext(), PaymentInfoActivity.class);
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
        if (withdrawalSwiperefreshlayout!=null) {
            withdrawalSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            withdrawalSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    withdrawalRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (withdrawalAdapter != null) {
                                withdrawalAdapter.notifyDataSetChanged();
                                withdrawalAdapter.setUpFetching(false);
                                withdrawalAdapter.setUpFetchEnable(false);
                            }
                            if (withdrawalSwiperefreshlayout!=null)
                            withdrawalSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        withdrawalAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        withdrawalAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            withdrawalAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, withdrawalRecyclerview);
        }
    }

    private void setLoadMore() {
        withdrawalRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    withdrawalAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    withdrawalAdapter.addData(list);
                    withdrawalAdapter.loadMoreComplete();
                }
            }
        }, 1500);
    }
}
