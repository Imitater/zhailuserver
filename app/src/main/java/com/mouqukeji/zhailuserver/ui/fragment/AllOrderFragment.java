package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.activity.AllOrderContract;
import com.mouqukeji.zhailuserver.model.activity.AllOrderModel;
import com.mouqukeji.zhailuserver.presenter.activity.AllOrderPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.AllOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllOrderFragment extends BaseFragment<AllOrderPresenter, AllOrderModel> implements AllOrderContract.View, View.OnClickListener {
    @BindView(R.id.allorder_recyclerview)
    RecyclerView allorderRecyclerview;
    @BindView(R.id.allorder_swiperefreshlayout)
    SwipeRefreshLayout allorderSwiperefreshlayout;
    private List list;
    private int page = 1;
    private AllOrderAdapter allOrderAdapter;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_allorder;
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
        allorderRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        allOrderAdapter = new AllOrderAdapter(R.layout.adapter_allorder, list);
        allorderRecyclerview.setAdapter(allOrderAdapter);
        allOrderAdapter.disableLoadMoreIfNotFullPage(allorderRecyclerview);
    }

    private void initData() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        allorderSwiperefreshlayout.setColorSchemeResources(R.color.blue);
        allorderSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                allorderRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (allOrderAdapter != null) {
                            allOrderAdapter.notifyDataSetChanged();
                            allOrderAdapter.setUpFetching(false);
                            allOrderAdapter.setUpFetchEnable(false);
                        }
                        allorderSwiperefreshlayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        allOrderAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        allOrderAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            allOrderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, allorderRecyclerview);
        }
    }

    private void setLoadMore() {
        allorderRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    allOrderAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    allOrderAdapter.addData(list);
                    allOrderAdapter.loadMoreComplete();
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
