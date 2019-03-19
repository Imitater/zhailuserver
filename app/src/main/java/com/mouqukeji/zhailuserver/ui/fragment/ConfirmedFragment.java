package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.base.BaseLazyFragment;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.contract.fragment.ConfirmedContract;
import com.mouqukeji.zhailuserver.model.fragment.ConfirmedModel;
import com.mouqukeji.zhailuserver.presenter.fragment.ConfirmedPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.ConfirmedAdapter;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import java.util.List;

import butterknife.BindView;

import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;


public class ConfirmedFragment extends BaseLazyFragment<ConfirmedPresenter, ConfirmedModel> implements ConfirmedContract.View, View.OnClickListener {
    @BindView(R.id.confirmed_recyclerview)
    RecyclerView confirmedRecyclerview;
    @BindView(R.id.confirmed_swiperefreshlayout)
    SwipeRefreshLayout confirmedSwiperefreshlayout;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    private int page = 1;
    private ConfirmedAdapter confirmedAdapter;
    private String spUserID;
    private List<PutOrderBean.OrdersListBean> ordersList;
    private int pages;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.putOrder(spUserID, "9", page + "", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_confirmed;
    }

    @Override
    protected void setUpView() {

    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        confirmedRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        confirmedAdapter = new ConfirmedAdapter(R.layout.adapter_confirme, ordersList);
        confirmedRecyclerview.setAdapter(confirmedAdapter);
        confirmedAdapter.disableLoadMoreIfNotFullPage(confirmedRecyclerview);
        confirmedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                mMvpPresenter.confirmFinish(confirmedAdapter.getData().get(i).getOrder_id(), mMultipleStateView);
            }
        });
    }


    private void initSwipeRefresh() {
        //设置下拉刷新
        if (confirmedSwiperefreshlayout != null) {
            confirmedSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            confirmedSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    confirmedRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page = 1;
                            mMvpPresenter.putOrder(spUserID, "9", page + "", mMultipleStateView);
                            if (confirmedAdapter != null) {
                                confirmedAdapter.notifyDataSetChanged();
                                confirmedAdapter.setUpFetching(false);
                                confirmedAdapter.setUpFetchEnable(false);
                            }
                            if (confirmedSwiperefreshlayout != null)
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
        if (ordersList.size() >= 10) {
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
                if (page > pages) {
                    //数据全部加载完毕
                    confirmedAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.putOrderNext(spUserID, "9", page + "", mMultipleStateView);
                }
            }
        }, 1500);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected void lazyLoad() {
        if (!mIsprepared || !mIsVisible || mHasLoadedOnce) {
            return;
        }
        mHasLoadedOnce = true;
        page=1;
        mMvpPresenter.putOrder(spUserID, "9", page + "", mMultipleStateView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void putOrder(PutOrderBean bean) {
        pages = bean.getPages();
        page++;
        if (llNoOrder != null && confirmedRecyclerview != null) {
            llNoOrder.setVisibility(View.GONE);
            confirmedRecyclerview.setVisibility(View.VISIBLE);
            ordersList = bean.getOrdersList();
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
        if (confirmedSwiperefreshlayout != null) {
            confirmedSwiperefreshlayout.setRefreshing(false);
        }
    }

    @Override
    public void putOrderNext(PutOrderBean bean) {
        page++;
        //成功获取更多数据
        confirmedAdapter.addData(ordersList);
        confirmedAdapter.loadMoreComplete();
    }

    @Override
    public void isEmpty() {
        if (llNoOrder != null && confirmedRecyclerview != null) {
            llNoOrder.setVisibility(View.VISIBLE);
            confirmedRecyclerview.setVisibility(View.GONE);
        }
        //设置下拉刷新
        initSwipeRefresh();
    }

    @Override
    public void confirmFinish(ConfirmFinishBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
    }
    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_Z) {
                page = 1;
                mMvpPresenter.putOrder(spUserID, "9", page + "", mMultipleStateView);
                confirmedAdapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    protected boolean isRegisteredEventBus() {
        return  true;
    }
}
