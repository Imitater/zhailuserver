package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.activity.ReceiptContract;
import com.mouqukeji.zhailuserver.model.activity.ReceiptModel;
import com.mouqukeji.zhailuserver.presenter.activity.ReceiptPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.ReceiptAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class ReceiptFragment extends BaseFragment<ReceiptPresenter, ReceiptModel> implements ReceiptContract.View, View.OnClickListener {
    @BindView(R.id.receipt_recyclerview)
    RecyclerView receiptRecyclerview;
    @BindView(R.id.receipt_swiperefreshlayout)
    SwipeRefreshLayout receiptSwiperefreshlayout;
    private List<Integer> list;
    private ReceiptAdapter receiptRecyclerviewAdapter;
    private int page = 1;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_receipt;
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
        receiptRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        receiptRecyclerviewAdapter = new ReceiptAdapter(R.layout.adapter_receipt, list);
        receiptRecyclerview.setAdapter(receiptRecyclerviewAdapter);
        receiptRecyclerviewAdapter.disableLoadMoreIfNotFullPage(receiptRecyclerview);
         //recyclerview 添加头布局
        View inflate = getLayoutInflater().inflate(R.layout.adapter_receipt_head, null);
        receiptRecyclerviewAdapter.addHeaderView(inflate);
        //设置头部局图片
        ImageView receiptAdIv = inflate.findViewById(R.id.receipt_ad_iv);
        Glide.with(getMContext()).load("https://img.zcool.cn/community/01c40855496e590000019ae939b281.jpg@1280w_1l_2o_100sh.jpg").into(receiptAdIv);
    }

    private void initData() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

    }


    private void initSwipeRefresh() {
        //设置下拉刷新
        receiptSwiperefreshlayout.setColorSchemeResources(R.color.blue);
        receiptSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                receiptRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (receiptRecyclerviewAdapter != null) {
                            receiptRecyclerviewAdapter.notifyDataSetChanged();
                            receiptRecyclerviewAdapter.setUpFetching(false);
                            receiptRecyclerviewAdapter.setUpFetchEnable(false);
                        }
                        receiptSwiperefreshlayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        receiptRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        receiptRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (list.size() >= 10) {
             receiptRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                     setLoadMore();//加载更多
                }
            }, receiptRecyclerview);
        }
    }

    private void setLoadMore() {
         receiptRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > 2) {
                    //数据全部加载完毕
                    receiptRecyclerviewAdapter.loadMoreEnd();
                } else {
                    page++;
                    //成功获取更多数据
                    receiptRecyclerviewAdapter.addData(list);
                    receiptRecyclerviewAdapter.loadMoreComplete();
                }
            }
        }, 1500);
    }
}
