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
import com.mouqukeji.zhailuserver.contract.fragment.PaymentAllContract;
import com.mouqukeji.zhailuserver.model.fragment.PaymentAllModel;
import com.mouqukeji.zhailuserver.presenter.fragment.PaymentAllPresenter;
import com.mouqukeji.zhailuserver.ui.activity.PaymentInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.PaymentAllAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class PaymentAllFragment extends BaseFragment<PaymentAllPresenter, PaymentAllModel> implements PaymentAllContract.View, View.OnClickListener {
    @BindView(R.id.paymentall_recyclerview)
    RecyclerView paymentallRecyclerview;
    List list = new ArrayList();
    @BindView(R.id.paymentall_swiperefreshlayout)
    SwipeRefreshLayout paymentallSwiperefreshlayout;
    private PaymentAllAdapter paymentAllAdapter;
    private int page=1;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_paymentall;
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
        paymentallRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        paymentAllAdapter = new PaymentAllAdapter(R.layout.adapter_payment_all, list);
        paymentallRecyclerview.setAdapter(paymentAllAdapter);
        paymentAllAdapter.disableLoadMoreIfNotFullPage(paymentallRecyclerview);
        //item点击事件
        paymentAllAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
        if (paymentallSwiperefreshlayout!=null) {
            paymentallSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            paymentallSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    paymentallRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
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


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        paymentAllAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        paymentAllAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            paymentAllAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, paymentallRecyclerview);
        }
    }

    private void setLoadMore() {
        paymentallRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    paymentAllAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    paymentAllAdapter.addData(list);
                    paymentAllAdapter.loadMoreComplete();
                }
            }
        }, 1500);
    }

}
