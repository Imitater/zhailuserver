package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;

import butterknife.BindView;


public class BigImageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bigimage_iv)
    ImageView bigimageIv;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_bigimage;
    }

    protected void setUpView() {
        Intent intent = getIntent();
        String pic = intent.getStringExtra("pic");
        ImageView bigImageIv = findViewById(R.id.bigimage_iv);
        //设置图片
        Glide.with(this).load(pic).into(bigImageIv);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {

    }


}
