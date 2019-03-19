package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.CashOutBean;
import com.mouqukeji.zhailuserver.contract.activity.CashOutContract;
import com.mouqukeji.zhailuserver.model.activity.CashOutModel;
import com.mouqukeji.zhailuserver.presenter.activity.CashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CashOutActivity extends BaseActivity<CashPresenter, CashOutModel> implements CashOutContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    ImageView actionBack;
    @BindView(R.id.payment_info_iv)
    ImageView paymentInfoIv;
    @BindView(R.id.payment_info_type)
    TextView paymentInfoType;
    @BindView(R.id.payment_info_money)
    TextView paymentInfoMoney;
    @BindView(R.id.payment_info_type_state)
    TextView paymentInfoTypeState;
    @BindView(R.id.payment_info_type_money)
    TextView paymentInfoTypeMoney;
    @BindView(R.id.payment_info_get_time)
    TextView paymentInfoGetTime;
    @BindView(R.id.payment_info_get_way)
    TextView paymentInfoGetWay;
    @BindView(R.id.payment_info_code)
    TextView paymentInfoCode;
    @BindView(R.id.payment_info_put_time)
    TextView paymentInfoPutTime;
    private String type;

    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        String bill_id = intent.getStringExtra("bill_id");
        mMvpPresenter.getCashOut(bill_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_cashout;
    }

    @Override
    protected void setUpView() {
        actionBack.setOnClickListener(this);
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

    @Override
    public void getCashOut(CashOutBean bean) {
        paymentInfoMoney.setText(bean.getServerBillDetail().getAmount());
        paymentInfoPutTime.setText(bean.getServerBillDetail().getCreate_time());
        paymentInfoGetTime.setText(bean.getServerBillDetail().getUpdate_time());
        paymentInfoGetWay.setText(bean.getServerBillDetail().getFtype());
        paymentInfoCode.setText(bean.getServerBillDetail().getForward_sn());
        paymentInfoTypeMoney.setText(bean.getServerBillDetail().getAmount());
    }


}
