package com.mouqukeji.zhailuserver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.HelpTakeInfoContract;
import com.mouqukeji.zhailuserver.contract.fragment.ReceiptContract;
import com.mouqukeji.zhailuserver.model.activity.HelpTakeInfoModel;
import com.mouqukeji.zhailuserver.model.fragment.ReceiptModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpTakeInfoPresenter;
import com.mouqukeji.zhailuserver.presenter.fragment.ReceiptPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpTakeInfoActivity extends BaseActivity <HelpTakeInfoPresenter, HelpTakeInfoModel> implements HelpTakeInfoContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helptake_name)
    TextView helptakeName;
    @BindView(R.id.helptake_address)
    TextView helptakeAddress;
    @BindView(R.id.helptake_type)
    TextView helptakeType;
    @BindView(R.id.helptake_code)
    TextView helptakeCode;
    @BindView(R.id.helptake_send_type)
    TextView helptakeSendType;
    @BindView(R.id.helptake_time)
    TextView helptakeTime;
    @BindView(R.id.helptake_sex)
    TextView helptakeSex;
    @BindView(R.id.helptake_remake)
    TextView helptakeRemake;
    @BindView(R.id.helptake_code_number)
    TextView helptakeCodeNumber;
    @BindView(R.id.helptake_creattime)
    TextView helptakeCreattime;
    @BindView(R.id.helptake_left)
    Button helptakeLeft;
    @BindView(R.id.helptake_right)
    Button helptakeRight;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helptake_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
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
