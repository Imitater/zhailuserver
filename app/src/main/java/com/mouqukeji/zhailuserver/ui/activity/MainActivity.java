package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.MainContract;
import com.mouqukeji.zhailuserver.model.activity.MainModel;
import com.mouqukeji.zhailuserver.presenter.activity.MainPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.LeftAdapter;
import com.mouqukeji.zhailuserver.ui.adapter.MainPagerAdapter;
import com.mouqukeji.zhailuserver.ui.fragment.OrderFragment;
import com.mouqukeji.zhailuserver.ui.fragment.ReceiptFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, View.OnClickListener ,ViewPager.OnPageChangeListener {
    @BindView(R.id.main_receipt_bt)
    RadioButton mainReceiptBt;
    @BindView(R.id.main_order_bt)
    RadioButton mainOrderBt;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.main_framelayout)
    FrameLayout mainFramelayout;
    @BindView(R.id.left_head)
    CircleImageView leftHead;
    @BindView(R.id.left_name)
    TextView leftName;
    @BindView(R.id.left_number)
    TextView leftNumber;
    @BindView(R.id.left_list)
    ListView leftList;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.main_user_left)
    Button mainUserLeft;
    private ActionBarDrawerToggle drawerbar;
    private List page;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        //侧拉菜单设置
        initDrawlayout();
        //侧拉菜单list设置
        setDrawList();
        //顶部导航默认设置
        initDefaul();
        //点击事件
        initListener();
        //设置头像
        Glide.with(this).load("http://img5.duitang.com/uploads/item/201409/23/20140923094045_BNYji.thumb.700_0.png").into(leftHead);
        //viewpager页面设置
        initPage();
        //设置viewpager
         setViewPager();
         //设置pageLIstener
        mainViewpager.setOnPageChangeListener(this);
    }

    private void setViewPager() {
        //刷新tab 下的fragment  重新设置adapter
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),page);
        mainViewpager.setAdapter(mainPagerAdapter);
        mainViewpager.setCurrentItem(0);
    }

    //加载viewpager 页面
    private void initPage() {
        page = new ArrayList<Fragment>();
        page.add(new ReceiptFragment());
        page.add(new OrderFragment());
    }

    private void setDrawList() {
        LeftAdapter leftAdapter = new LeftAdapter(this);
        leftList.setAdapter(leftAdapter);
        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //左侧list点击事件
                listClock(position);
                drawerlayout.closeDrawer(mainFramelayout);
            }
        });
    }

    private void listClock(int position) {
        switch (position){
            case 0:
                Intent intent = new Intent(MainActivity.this, IdentityActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent1);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void initListener() {
        mainUserLeft.setOnClickListener(this);
        mainReceiptBt.setOnClickListener(this);
        mainOrderBt.setOnClickListener(this);
    }

    private void initDefaul() {
        //默认选中接单 背景色 蓝色
        mainReceiptBt.setTextColor(getResources().getColor(R.color.blue));
        mainOrderBt.setTextColor(getResources().getColor(R.color.main_gray));
    }

    private void initDrawlayout() {
        drawerbar = new ActionBarDrawerToggle(this, drawerlayout, null, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerlayout.setDrawerListener(drawerbar);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_user_left:
                drawerlayout.openDrawer(mainFramelayout);
                break;
            case R.id.main_receipt_bt://接单点击
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.blue));
                mainOrderBt.setTextColor(getResources().getColor(R.color.main_gray));
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.main_order_bt://订单点击
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.main_gray));
                mainOrderBt.setTextColor(getResources().getColor(R.color.blue));
                mainViewpager.setCurrentItem(1);
                break;
        }
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.blue));
                mainOrderBt.setTextColor(getResources().getColor(R.color.main_gray));
                break;
            case 1:
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.main_gray));
                mainOrderBt.setTextColor(getResources().getColor(R.color.blue));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
