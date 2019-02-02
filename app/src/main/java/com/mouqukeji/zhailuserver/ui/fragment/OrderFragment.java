package com.mouqukeji.zhailuserver.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.activity.OrderContract;
import com.mouqukeji.zhailuserver.model.activity.OrderModel;
import com.mouqukeji.zhailuserver.presenter.activity.OrderPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.OrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderFragment extends BaseFragment<OrderPresenter, OrderModel> implements OrderContract.View, View.OnClickListener {
    @BindView(R.id.order_tablayout)
    TabLayout orderTablayout;
    @BindView(R.id.order_viewpager)
    ViewPager orderViewpager;
    ArrayList<Fragment> pageList = new ArrayList();
    ArrayList<String> title = new ArrayList<>();

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_order;
    }

    @Override
    protected void setUpView() {
        //添加fragment page页面
        initPage();
        //设置viewpager
        setViewpager();
    }

    private void initPage() {
        title.add("全部");
        title.add("待取货");
        title.add("配送中");
        title.add("待确认");

        pageList.add(new AllOrderFragment());
        pageList.add(new PickUpFragment());
        pageList.add(new DistributionFragment());
        pageList.add(new ConfirmedFragment());
    }

    //Viewpager的初始化和相关加载
    private void setViewpager() {
        OrderAdapter orderAdapter = new OrderAdapter(getActivity().getSupportFragmentManager(), title, pageList);
        orderViewpager.setAdapter(orderAdapter);
        orderTablayout.setupWithViewPager(orderViewpager);
        orderViewpager.setOffscreenPageLimit(1);//ViewPager设置预加载页面的个数方法
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

    }

}
