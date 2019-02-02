package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.activity.PickUpContract;
import com.mouqukeji.zhailuserver.model.activity.PickUpModel;
import com.mouqukeji.zhailuserver.presenter.activity.PickUpPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.PickUpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class PickUpFragment extends BaseFragment<PickUpPresenter, PickUpModel> implements PickUpContract.View, View.OnClickListener {
    @BindView(R.id.pickup_recyclerview)
    RecyclerView pickupRecyclerview;
    @BindView(R.id.pickup_swiperefreshlayout)
    SwipeRefreshLayout pickupSwiperefreshlayout;
    private List list;
    private int page = 1;
    private PickUpAdapter pickUpAdapter;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_pickup;
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
        pickupRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        pickUpAdapter = new PickUpAdapter(R.layout.adapter_pickup, list);
        pickupRecyclerview.setAdapter(pickUpAdapter);
        pickUpAdapter.disableLoadMoreIfNotFullPage(pickupRecyclerview);
    }

    private void initData() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        pickupSwiperefreshlayout.setColorSchemeResources(R.color.blue);
        pickupSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pickupRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pickUpAdapter != null) {
                            pickUpAdapter.notifyDataSetChanged();
                            pickUpAdapter.setUpFetching(false);
                            pickUpAdapter.setUpFetchEnable(false);
                        }
                        pickupSwiperefreshlayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        pickUpAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        pickUpAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            pickUpAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, pickupRecyclerview);
        }
    }

    private void setLoadMore() {
        pickupRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    pickUpAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    pickUpAdapter.addData(list);
                    pickUpAdapter.loadMoreComplete();
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
