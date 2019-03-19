package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.contract.activity.MyInfoContract;
import com.mouqukeji.zhailuserver.model.activity.MyInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.MyInfoPresenter;
import com.mouqukeji.zhailuserver.ui.widget.GlideBlurformation;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import java.text.NumberFormat;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyInfoActivity extends BaseActivity<MyInfoPresenter, MyInfoModel> implements MyInfoContract.View, View.OnClickListener {
    @BindView(R.id.myinfo_backage)
    ImageView myinfoBackage;
    @BindView(R.id.myinfo_head)
    CircleImageView myinfoHead;
    @BindView(R.id.myinfo_edit)
    LinearLayout myinfoEdit;
    @BindView(R.id.myinfo_name)
    TextView myinfoName;
    @BindView(R.id.myinfo_order_count)
    TextView myinfoOrderCount;
    @BindView(R.id.myinfo_ontime)
    TextView myinfoOntime;
    @BindView(R.id.myinfo_score)
    TextView myinfoScore;
    @BindView(R.id.myinfo_user_evaluation)
    LinearLayout myinfoUserEvaluation;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getInfo(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_myinfo;
    }

    @Override
    protected void setUpView() {
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
        switch (v.getId()) {
            case R.id.myinfo_edit:
                Intent intent = new Intent(MyInfoActivity.this, InformationActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getInfo(InfoBean bean) {
        //设置背景
        Glide.with(this)
                .load(bean.getUserInfo().getAvatar())
                .apply(RequestOptions.bitmapTransform(new GlideBlurformation(this)))
                .into(myinfoBackage);

        //设置头像
        Glide.with(this).load(bean.getUserInfo().getAvatar()).into(myinfoHead);
        myinfoName.setText(bean.getUserInfo().getNickname());//昵称
        myinfoOrderCount.setText(bean.getUserInfo().getOrder_num() + "");//订单量
        myinfoOntime.setText(bean.getUserInfo().getPunctual() + "");//准确率
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        myinfoScore.setText(nf.format(Double.parseDouble(bean.getUserInfo().getAvg() )));//平分
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_B) {
                //刷新头像
                mMvpPresenter.getInfo(spUserID, mMultipleStateView);
            }
        }
    }

}
