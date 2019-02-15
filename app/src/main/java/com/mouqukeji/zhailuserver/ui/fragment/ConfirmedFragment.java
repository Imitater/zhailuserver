package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.fragment.ConfirmedContract;
import com.mouqukeji.zhailuserver.model.fragment.ConfirmedModel;
import com.mouqukeji.zhailuserver.presenter.fragment.ConfirmedPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.ConfirmedAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ConfirmedFragment extends BaseFragment<ConfirmedPresenter, ConfirmedModel> implements ConfirmedContract.View, View.OnClickListener {
    @BindView(R.id.confirmed_recyclerview)
    RecyclerView confirmedRecyclerview;
    @BindView(R.id.confirmed_swiperefreshlayout)
    SwipeRefreshLayout confirmedSwiperefreshlayout;
    private int page = 1;
    private ConfirmedAdapter confirmedAdapter;
    private List list;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_confirmed;
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
        confirmedRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        confirmedAdapter = new ConfirmedAdapter(R.layout.adapter_pickup, list);
        confirmedRecyclerview.setAdapter(confirmedAdapter);
        confirmedAdapter.disableLoadMoreIfNotFullPage(confirmedRecyclerview);
    }

    private void initData() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        if (confirmedSwiperefreshlayout!=null) {
            confirmedSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            confirmedSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    confirmedRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (confirmedAdapter != null) {
                                confirmedAdapter.notifyDataSetChanged();
                                confirmedAdapter.setUpFetching(false);
                                confirmedAdapter.setUpFetchEnable(false);
                            }
                            if (confirmedSwiperefreshlayout!=null)
                            confirmedSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        confirmedAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        confirmedAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
            confirmedAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, confirmedRecyclerview);
        }
    }

    private void setLoadMore() {
        confirmedRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    confirmedAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    confirmedAdapter.addData(list);
                    confirmedAdapter.loadMoreComplete();
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
