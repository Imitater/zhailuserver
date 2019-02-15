package com.mouqukeji.zhailuserver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.WithdrawalAContract;
import com.mouqukeji.zhailuserver.model.activity.WithdrawalAModel;
import com.mouqukeji.zhailuserver.presenter.activity.WithdrawalAPresenter;
import com.mouqukeji.zhailuserver.ui.fragment.AccountFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithdrawalActivity extends BaseActivity<WithdrawalAPresenter, WithdrawalAModel> implements WithdrawalAContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.withdrawal_line)
    View withdrawalLine;
    @BindView(R.id.withdrawal_item)
    LinearLayout withdrawalItem;
    @BindView(R.id.withdrawal_bt)
    Button withdrawalBt;
    @BindView(R.id.withdrawal_framelayout)
    FrameLayout withdrawalFramelayout;
    @BindView(R.id.withdrawal_next)
    ImageView withdrawalNext;
    @BindView(R.id.withdrawal_show)
    LinearLayout withdrawalShow;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("提现账户");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        withdrawalBt.setOnClickListener(this);
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
            case R.id.withdrawal_bt:
                withdrawalFramelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.withdrawal_framelayout, new AccountFragment(), "account").commit();
                break;
        }
    }

}
