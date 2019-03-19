package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.SettingContract;
import com.mouqukeji.zhailuserver.model.activity.SettingModel;
import com.mouqukeji.zhailuserver.presenter.activity.SettingPresenter;
import com.mouqukeji.zhailuserver.utils.LoginQuit;
import com.mouqukeji.zhailuserver.utils.PhoneUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<SettingPresenter, SettingModel> implements SettingContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.setting_change_password)
    LinearLayout settingChangePassword;
    @BindView(R.id.setting_question)
    LinearLayout settingQuestion;
    @BindView(R.id.setting_advice)
    LinearLayout settingAdvice;
    @BindView(R.id.setting_about)
    LinearLayout settingAbout;
    @BindView(R.id.setting_bt)
    Button settingBt;
    @BindView(R.id.setting_version)
    TextView settingVersion;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("系统设置");
        settingVersion.setText("版本"+PhoneUtils.getVersionName(this));
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        settingChangePassword.setOnClickListener(this);
        settingQuestion.setOnClickListener(this);
        settingAdvice.setOnClickListener(this);
        settingAbout.setOnClickListener(this);
        settingBt.setOnClickListener(this);
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
            case R.id.setting_change_password:
                //修改密码
                Intent intent = new Intent(SettingActivity.this, ChangePwActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_question://常见问题
                Intent intent1 = new Intent(SettingActivity.this, QuestionActivity.class);
                startActivity(intent1);
                break;
            case R.id.setting_advice://意见反馈
                Intent intent2 = new Intent(SettingActivity.this, AdviceActivity.class);
                startActivity(intent2);
                break;
            case R.id.setting_about://关于
                Intent intent3 = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent3);
                break;
            case R.id.setting_bt://退出登录
                LoginQuit.loginQuit(SettingActivity.this, 0);
                break;
        }
    }

}
