package com.mouqukeji.zhailuserver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.about_versiom)
    LinearLayout aboutVersiom;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_about;
    }

    @Override
    protected void setUpView() {
        setListener();
        //设置title
        actionTitle.setText("关于宅鹿");
    }

    private void setListener() {
        aboutVersiom.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_versiom:
                Toast.makeText(AboutActivity.this, "当前已是最新版", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }


}
