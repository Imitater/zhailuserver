package com.mouqukeji.zhailuserver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.PaymentInfoContract;
import com.mouqukeji.zhailuserver.model.activity.PaymentInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.PaymentInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentInfoActivity extends BaseActivity<PaymentInfoPresenter, PaymentInfoModel> implements PaymentInfoContract.View, View.OnClickListener {
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
    @BindView(R.id.payment_info_put_time)
    TextView paymentInfoPutTime;
    @BindView(R.id.payment_info_get_time)
    TextView paymentInfoGetTime;
    @BindView(R.id.payment_info_type_way)
    TextView paymentInfoTypeWay;
    @BindView(R.id.payment_info_code)
    TextView paymentInfoCode;
    @BindView(R.id.action_back)
    ImageView actionBack;

    @Override
    protected void initViewAndEvents() {

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
        switch (v.getId()){
            case R.id.action_back:
                finish();
                break;
        }
    }


}
