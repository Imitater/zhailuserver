package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.WithdrawalBean;
import com.mouqukeji.zhailuserver.contract.activity.PaymentInfoContract;
import com.mouqukeji.zhailuserver.model.activity.PaymentInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.PaymentInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentInfoActivity extends BaseActivity<PaymentInfoPresenter, PaymentInfoModel> implements PaymentInfoContract.View, View.OnClickListener {
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
    @BindView(R.id.payment_info_code)
    TextView paymentInfoCode;
    private String type;

    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        String bill_id = intent.getStringExtra("bill_id");
        type = intent.getStringExtra("type");
        mMvpPresenter.getWithdrawal(bill_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_paymentinfo;
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
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
        }
    }


    @Override
    public void getWithdrawal(WithdrawalBean bean) {
        if (type.equals("1")) {
            //接单收入
            paymentInfoType.setText("收入");//top 类型
            paymentInfoMoney.setText(bean.getServerBillDetail().getTask_price());//money
            //money
            paymentInfoTypeMoney.setText(bean.getServerBillDetail().getTask_price());
            //到账时间
            paymentInfoGetTime.setText(bean.getServerBillDetail().getFinish_time());
            //提现单号
            paymentInfoCode.setText(bean.getServerBillDetail().getOrder_sn());
        } else {
            //接单收入
            paymentInfoType.setText("收入");//top 类型
            paymentInfoMoney.setText(bean.getServerBillDetail().getMakeup_fee());//money
            //money
            paymentInfoTypeMoney.setText(bean.getServerBillDetail().getMakeup_fee());
            //到账时间
            paymentInfoGetTime.setText(bean.getServerBillDetail().getFinish_time());
            //提现单号
            paymentInfoCode.setText(bean.getServerBillDetail().getOrder_sn());
        }
    }

}
