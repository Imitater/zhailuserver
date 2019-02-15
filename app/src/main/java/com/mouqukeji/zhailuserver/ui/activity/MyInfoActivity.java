package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.MyInfoContract;
import com.mouqukeji.zhailuserver.model.activity.MyInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.MyInfoPresenter;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends BaseActivity<MyInfoPresenter, MyInfoModel> implements MyInfoContract.View, View.OnClickListener {
    @BindView(R.id.myinfo_backage)
    ImageView myinfoBackage;
    @BindView(R.id.myinfo_head)
    CircleImageView myinfoHead;
    @BindView(R.id.myinfo_edit)
    LinearLayout myinfoEdit;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_myinfo;
    }

    @Override
    protected void setUpView() {
        Glide.with(this).load("http://img3.imgtn.bdimg.com/it/u=2355087330,2927744035&fm=26&gp=0.jpg").into(myinfoBackage);
        Glide.with(this).load("http://img3.imgtn.bdimg.com/it/u=2355087330,2927744035&fm=26&gp=0.jpg").into(myinfoHead);
        initListener();
    }

    private void initListener() {
        myinfoEdit.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myinfo_edit:
                Intent intent = new Intent(MyInfoActivity.this, InformationActivity.class);
                startActivity(intent);
                break;
        }
    }

}
