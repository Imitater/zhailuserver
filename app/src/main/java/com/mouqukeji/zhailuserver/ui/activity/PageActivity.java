package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.BalanceBean;
import com.mouqukeji.zhailuserver.contract.activity.PageContract;
import com.mouqukeji.zhailuserver.model.activity.PackModel;
import com.mouqukeji.zhailuserver.presenter.activity.PagePresenter;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageActivity extends BaseActivity<PagePresenter, PackModel> implements PageContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.package_shouzhi)
    LinearLayout packageShouzhi;
    @BindView(R.id.package_tixian)
    LinearLayout packageTixian;
    @BindView(R.id.package_tixian_bt)
    TextView packageTixianBt;
    @BindView(R.id.package_balance)
    TextView packageBalance;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getBalance(spUserID,mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_package;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("我的钱包");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        packageShouzhi.setOnClickListener(this);
        packageTixian.setOnClickListener(this);
        packageTixianBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.package_shouzhi:
                //收支明细
                Intent intent = new Intent(PageActivity.this, PaymentActvitiy.class);
                startActivity(intent);
                break;
            case R.id.package_tixian:
                //提现账户
                Intent intent1 = new Intent(PageActivity.this, WithdrawalActivity.class);
                startActivity(intent1);
                break;
            case R.id.package_tixian_bt://提现
                Intent intent2 = new Intent(PageActivity.this, BalanceActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void getBalance(BalanceBean balanceBean) {
        packageBalance.setText(balanceBean.getBalance());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mMvpPresenter.getBalance(spUserID,mMultipleStateView);
    }
}
