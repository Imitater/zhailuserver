package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.fragment.DistributionContract;
import com.mouqukeji.zhailuserver.model.fragment.DistributionModel;
import com.mouqukeji.zhailuserver.presenter.fragment.DistributionPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.DistributionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DistributionFragment extends BaseFragment<DistributionPresenter, DistributionModel> implements DistributionContract.View, View.OnClickListener {
    @BindView(R.id.distribution_recyclerview)
    RecyclerView distributionRecyclerview;
    @BindView(R.id.distribution_swiperefreshlayout)
    SwipeRefreshLayout distributionSwiperefreshlayout;
    private int page = 1;
    private List list;
    private DistributionAdapter distributionAdapter;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_distribution;
    }

    @Override
    protected void setUpView() {
        //设置假数据
        initData();
        //设置recyclerview
        setRecyclerview();
        //设置上拉加载
        setUpLoad();
        //设置下拉刷新
        initSwipeRefresh();
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        distributionRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        distributionAdapter = new DistributionAdapter(R.layout.adapter_allorder, list);
        distributionRecyclerview.setAdapter(distributionAdapter);
        distributionAdapter.disableLoadMoreIfNotFullPage(distributionRecyclerview);
    }

    private void initData() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        if (distributionSwiperefreshlayout!=null) {
            distributionSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            distributionSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    distributionRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (distributionAdapter != null) {
                                distributionAdapter.notifyDataSetChanged();
                                distributionAdapter.setUpFetching(false);
                                distributionAdapter.setUpFetchEnable(false);
                            }
                            if (distributionSwiperefreshlayout!=null)
                            distributionSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        distributionAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        distributionAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            distributionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, distributionRecyclerview);
        }
    }

    private void setLoadMore() {
        distributionRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    distributionAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    distributionAdapter.addData(list);
                    distributionAdapter.loadMoreComplete();
                }
            }
        }, 1500);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

    }
}
