package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.model.LatLng;
import com.luck.picture.lib.entity.LocalMedia;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeliverOrderInfoBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpDeliverInfoContract;
import com.mouqukeji.zhailuserver.model.activity.HelpDeliverInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpDeliverInfoPresenter;
import com.mouqukeji.zhailuserver.utils.DialogUtils;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.OpenLocalMapUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;

public class HelpDeliverInfoActivity extends BaseActivity<HelpDeliverInfoPresenter, HelpDeliverInfoModel> implements HelpDeliverInfoContract.View, View.OnClickListener {

    List<LocalMedia> list = new ArrayList<>();
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helpdeliver_price)
    TextView helpdeliverPrice;
    @BindView(R.id.helpdeliver_top_name)
    TextView helpdeliverTopName;
    @BindView(R.id.helpdeliver_top_phone)
    ImageView helpdeliverTopPhone;
    @BindView(R.id.helpdeliver_top_address)
    TextView helpdeliverTopAddress;
    @BindView(R.id.helpdeliver_bottom_name)
    TextView helpdeliverBottomName;
    @BindView(R.id.helpdeliver_bottom_phone)
    ImageView helpdeliverBottomPhone;
    @BindView(R.id.helpdeliver_bottom_address)
    TextView helpdeliverBottomAddress;
    @BindView(R.id.helpdeliver_type)
    TextView helpdeliverType;
    @BindView(R.id.helpdeliver_time)
    TextView helpdeliverTime;
    @BindView(R.id.helpdeliver_remake)
    TextView helpdeliverRemake;
    @BindView(R.id.helpdeliver_code_number)
    TextView helpdeliverCodeNumber;
    @BindView(R.id.helpdeliver_creattime)
    TextView helpdeliverCreattime;
    @BindView(R.id.helpdeliver_left)
    Button helpdeliverLeft;
    @BindView(R.id.helpdeliver_right)
    Button helpdeliverRight;
    private String progress;
    private String order_id;
    private String spUserID;
    private String url = "";
    private String money = "-1";
    private String start_telephone;
    private String telephone;
    private long sid;
    private String end_address;
    private String end_lng;
    private String end_lat;
    private String start_address;
    private String start_lng;
    private String start_lat;

    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        spUserID = new GetSPData().getSPUserID(this);
        order_id = intent.getStringExtra("order_id");
        String cate_id = intent.getStringExtra("cate_id");
        progress = intent.getStringExtra("progress");
        if (progress.equals("3")) {
            helpdeliverRight.setText("确认取件");
        } else if (progress.equals("8")) {
            helpdeliverRight.setText("确认送达");
        } else if (progress.equals("9")) {
            helpdeliverRight.setText("确认完成");
        } else {
            helpdeliverRight.setVisibility(View.GONE);
        }
        mMvpPresenter.deliverOrderInfo(order_id, cate_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpdeliver_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        helpdeliverLeft.setOnClickListener(this);
        helpdeliverPrice.setOnClickListener(this);
        helpdeliverRight.setOnClickListener(this);
        helpdeliverTopPhone.setOnClickListener(this);
        helpdeliverBottomPhone.setOnClickListener(this);
        helpdeliverTopAddress.setOnClickListener(this);
        helpdeliverBottomAddress.setOnClickListener(this);
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
            case R.id.helpdeliver_left:
                //返回接单
                finish();
                break;
            case R.id.helpdeliver_right:
                //判断当前状态  取件/寄出
                //判断类型 接单状态 /已接单状态
                if (progress.equals("3")) {
                    mMvpPresenter.confirmPurchase(order_id, spUserID, "", "0", mMultipleStateView);
                } else if (progress.equals("8")) {
                    //确认送达接口
                    mMvpPresenter.confirmService(order_id, mMultipleStateView);
                } else if (progress.equals("9")) {
                    //已完成接口
                    mMvpPresenter.confirmFinish(order_id, mMultipleStateView);
                }
                break;
            case R.id.helpdeliver_top_phone:
                View dialog_iscall = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall, true, true, start_telephone);
                break;
            case R.id.helpdeliver_bottom_phone:
                View dialog_iscall1 = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall1, true, true, telephone);
                break;
            case R.id.helpdeliver_top_address:
                LatLng latLng = new LatLng(Double.parseDouble(start_lat), Double.parseDouble(start_lng));
                OpenLocalMapUtil.goToGaodeMap(this,latLng,start_address);
                break;
            case R.id.helpdeliver_bottom_address:
                LatLng latLng1 = new LatLng(Double.parseDouble(end_lat), Double.parseDouble(end_lng));
                OpenLocalMapUtil.goToGaodeMap(this,latLng1,end_address);
                break;
        }
    }


    @Override
    public void deliverOrderInfo(DeliverOrderInfoBean bean) {
        //取 经纬度
        start_lat = bean.getDetail().getStart_lat();
        start_lng = bean.getDetail().getStart_lng();
        start_address = bean.getDetail().getStart_address();
        //收经纬度
        end_lat = bean.getDetail().getEnd_lat();
        end_lng = bean.getDetail().getEnd_lng();
        end_address = bean.getDetail().getEnd_address();

        //服务价格
        helpdeliverPrice.setText(bean.getDetail().getPay_fee());
        //取件人姓名
        helpdeliverTopName.setText(bean.getDetail().getStart_name());
        //取件人地址
        helpdeliverTopAddress.setText(bean.getDetail().getStart_address() + bean.getDetail().getStart_detail());
        //收件人姓名
        helpdeliverBottomName.setText(bean.getDetail().getEnd_name());
        //收件人地址
        helpdeliverBottomAddress.setText(bean.getDetail().getEnd_address() + bean.getDetail().getEnd_detail());
        //物品类型
        helpdeliverType.setText(bean.getDetail().getType_name());
        //揽件时间
        helpdeliverTime.setText(bean.getDetail().getDelivery_time());
        //备注
        helpdeliverRemake.setText(bean.getDetail().getRemarks());
        //寄件人电话
        start_telephone = bean.getDetail().getStart_telephone();
        //收件人电话
        telephone = bean.getDetail().getEnd_telephone();
        //编号
        helpdeliverCodeNumber.setText(bean.getDetail().getOrder_sn());
        //创建时间
        helpdeliverCreattime.setText(bean.getDetail().getCreate_time());
    }
    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }
    @Override
    public void confirmPurchase(ConfirmGetBuyBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
        finish();
    }

    @Override
    public void confirmService(ConfirmServiceBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
        finish();
    }

    @Override
    public void confirmFinish(ConfirmFinishBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
        finish();
    }

}
