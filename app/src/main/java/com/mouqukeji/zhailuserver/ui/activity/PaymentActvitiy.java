package com.mouqukeji.zhailuserver.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;
import com.mouqukeji.zhailuserver.contract.activity.PaymentContract;
import com.mouqukeji.zhailuserver.model.activity.PaymentModel;
import com.mouqukeji.zhailuserver.presenter.activity.PaymentPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.PaymentPagerAdapter;
import com.mouqukeji.zhailuserver.ui.fragment.IncomeFragment;
import com.mouqukeji.zhailuserver.ui.fragment.PaymentAllFragment;
import com.mouqukeji.zhailuserver.ui.fragment.WithdrawalFragment;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PaymentActvitiy extends BaseActivity<PaymentPresenter, PaymentModel> implements PaymentContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.payment_tablayout)
    TabLayout paymentTablayout;
    @BindView(R.id.payment_viewpager)
    ViewPager paymentViewpager;
    List<Fragment> fragmentList=new ArrayList();
    List<String> fragmentTitle=new ArrayList();

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_payment;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("收支明细");
        initFragment();
        initViewPage();
        actionBack.setOnClickListener(this);
    }
    //设置viewpager+tablayout
    private void initViewPage() {
        paymentViewpager.setAdapter(new PaymentPagerAdapter(getSupportFragmentManager(),this,fragmentList,fragmentTitle));
        paymentTablayout.setupWithViewPager(paymentViewpager);//此方法就是让tablayout和ViewPager联动
    }
    //添加数据
    private void initFragment() {
        fragmentList.add(new PaymentAllFragment());
        fragmentList.add(new IncomeFragment());
        fragmentList.add(new WithdrawalFragment());
        fragmentTitle.add("全部");
        fragmentTitle.add("已收入");
        fragmentTitle.add("提现");
    }



    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_back:
                finish();
                break;
        }
    }


}
