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
import com.mouqukeji.zhailuserver.contract.fragment.IncomeContract;
import com.mouqukeji.zhailuserver.model.fragment.IncomeModel;
import com.mouqukeji.zhailuserver.presenter.fragment.IncomePresenter;
import com.mouqukeji.zhailuserver.ui.activity.PaymentInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.IncomeAdapter;
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
    List list = new ArrayList();
    private int page=1;
    private IncomeAdapter incomeAdapter;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_income;
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
        incomeRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        incomeAdapter = new IncomeAdapter(R.layout.adapter_payment_all, list);
        incomeRecyclerview.setAdapter(incomeAdapter);
        incomeAdapter.disableLoadMoreIfNotFullPage(incomeRecyclerview);
        incomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
        if (incomeSwiperefreshlayout!=null) {
            incomeSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            incomeSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    incomeRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
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


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        incomeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        incomeAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            incomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, incomeRecyclerview);
        }
    }

    private void setLoadMore() {
        incomeRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    incomeAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    incomeAdapter.addData(list);
                    incomeAdapter.loadMoreComplete();
                }
            }
        }, 1500);
    }
}
