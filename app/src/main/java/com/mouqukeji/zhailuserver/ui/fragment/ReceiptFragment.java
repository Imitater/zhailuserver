package com.mouqukeji.zhailuserver.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.bean.AcceptOrderBean;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.contract.fragment.ReceiptContract;
import com.mouqukeji.zhailuserver.model.fragment.ReceiptModel;
import com.mouqukeji.zhailuserver.presenter.fragment.ReceiptPresenter;
import com.mouqukeji.zhailuserver.ui.activity.HelpBuyInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpDeliverInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpSendInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpTakeInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpUniversalInfoActivity;
import com.mouqukeji.zhailuserver.ui.adapter.ReceiptAdapter;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.SpUtils;

import java.util.List;

import butterknife.BindView;


public class ReceiptFragment extends BaseFragment<ReceiptPresenter, ReceiptModel> implements ReceiptContract.View, View.OnClickListener {
    @BindView(R.id.receipt_recyclerview)
    RecyclerView receiptRecyclerview;
    @BindView(R.id.receipt_swiperefreshlayout)
    SwipeRefreshLayout receiptSwiperefreshlayout;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    @BindView(R.id.receipt_ad_iv)
    ImageView receiptAdIv;
    private ReceiptAdapter receiptRecyclerviewAdapter;
    private int page = 1;
    private String lon;
    private String lat;
    private String spUserID;
    private List<GetOrderBean.OrdersListBean> list;
    private int pages;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        lat = (String) SpUtils.getString("lat", getMContext());
        lon = (String) SpUtils.getString("lon", getMContext());
        mMvpPresenter.getOrder(spUserID, lat, lon, page + "", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_receipt;
    }

    @Override
    protected void setUpView() {
        //设置头部局图片
        Glide.with(getMContext()).load(R.mipmap.mipmap_banner).into(receiptAdIv);
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        receiptRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        receiptRecyclerviewAdapter = new ReceiptAdapter(R.layout.adapter_receipt, list);
        receiptRecyclerview.setAdapter(receiptRecyclerviewAdapter);
        receiptRecyclerviewAdapter.disableLoadMoreIfNotFullPage(receiptRecyclerview);
        //点击接单
        receiptRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //接单 接口
                mMvpPresenter.acceptOrder(receiptRecyclerviewAdapter.getData().get(i).getOrder_id(), spUserID, mMultipleStateView);
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
        if (receiptSwiperefreshlayout != null) {
            receiptSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            receiptSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    receiptRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page = 1;
                            mMvpPresenter.getOrder(spUserID, lat, lon, page + "", mMultipleStateView);
                            if (receiptRecyclerviewAdapter != null) {
                                receiptRecyclerviewAdapter.notifyDataSetChanged();
                                receiptRecyclerviewAdapter.setUpFetching(false);
                                receiptRecyclerviewAdapter.setUpFetchEnable(false);
                            }
                            if (receiptSwiperefreshlayout != null)
                                receiptSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
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
                if (page > pages) {
                    //数据全部加载完毕
                    receiptRecyclerviewAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getOrderNext(spUserID, lat, lon, page + "", mMultipleStateView);
                }
            }
        }, 1500);
    }

    @Override
    public void getOrder(GetOrderBean bean) {
        page++;
        pages = bean.getPages();
        if (llNoOrder != null && receiptRecyclerview != null) {
            llNoOrder.setVisibility(View.GONE);
            receiptRecyclerview.setVisibility(View.VISIBLE);
            list = bean.getOrdersList();
            //设置recyclerview
            setRecyclerview();
        }
        //设置上拉加载
        setUpLoad();
        //设置下拉刷新
        initSwipeRefresh();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (receiptSwiperefreshlayout != null) {
            receiptSwiperefreshlayout.setRefreshing(false);
        }
    }

    @Override
    public void getOrderNext(GetOrderBean bean) {
        //成功获取更多数据
        receiptRecyclerviewAdapter.addData(bean.getOrdersList());
        receiptRecyclerviewAdapter.loadMoreComplete();
        page++;
    }

    @Override
    public void isEmpty() {
        if (llNoOrder != null && receiptRecyclerview != null) {
            llNoOrder.setVisibility(View.VISIBLE);
            receiptRecyclerview.setVisibility(View.GONE);
        }
        initSwipeRefresh();
    }

    @Override
    public void acceptOrder(AcceptOrderBean bean) {
        Toast.makeText(getMContext(), "接单成功", Toast.LENGTH_SHORT).show();
        //接单成功 发送消息

        page = 1;
        mMvpPresenter.getOrder(spUserID, lat, lon, page + "", mMultipleStateView);
        receiptRecyclerviewAdapter.notifyDataSetChanged();
        if (bean.getCate_id().equals("11")) {
            Intent intent = new Intent(getMContext(), HelpTakeInfoActivity.class);
            intent.putExtra("order_id", bean.getOrder_id());
            intent.putExtra("cate_id", bean.getCate_id());
            intent.putExtra("progress", bean.getProgress());
            getMContext().startActivity(intent);
        } else if (bean.getCate_id().equals("12")) {
            Intent intent = new Intent(getMContext(), HelpBuyInfoActivity.class);
            intent.putExtra("order_id", bean.getOrder_id());
            intent.putExtra("cate_id", bean.getCate_id());
            intent.putExtra("progress", bean.getProgress());
            getMContext().startActivity(intent);
        } else if (bean.getCate_id().equals("13")) {
            Intent intent = new Intent(getMContext(), HelpSendInfoActivity.class);
            intent.putExtra("order_id", bean.getOrder_id());
            intent.putExtra("cate_id", bean.getCate_id());
            intent.putExtra("progress", bean.getProgress());
            getMContext().startActivity(intent);
        } else if (bean.getCate_id().equals("14")) {
            Intent intent = new Intent(getMContext(), HelpDeliverInfoActivity.class);
            intent.putExtra("order_id", bean.getOrder_id());
            intent.putExtra("cate_id", bean.getCate_id());
            intent.putExtra("progress", bean.getProgress());
            getMContext().startActivity(intent);
        } else if (bean.getCate_id().equals("15")) {
            Intent intent = new Intent(getMContext(), HelpUniversalInfoActivity.class);
            intent.putExtra("order_id", bean.getOrder_id());
            intent.putExtra("cate_id", bean.getCate_id());
            intent.putExtra("progress", bean.getProgress());
            getMContext().startActivity(intent);
        }
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void rushOrder() {
        Toast.makeText(getMContext(), "手慢了，已经被抢了!", Toast.LENGTH_SHORT).show();
        //刷新列表
        page = 1;
        mMvpPresenter.getOrder(spUserID, lat, lon, page + "", mMultipleStateView);
        receiptRecyclerviewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_Z) {
                page = 1;
                mMvpPresenter.getOrder(spUserID, lat, lon, page + "", mMultipleStateView);
                receiptRecyclerviewAdapter.notifyDataSetChanged();
            }
        }
    }

}
