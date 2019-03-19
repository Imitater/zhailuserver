package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.TakeOrderInfoBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpTakeInfoContract;
import com.mouqukeji.zhailuserver.model.activity.HelpTakeInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpTakeInfoPresenter;
import com.mouqukeji.zhailuserver.utils.DialogUtils;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.OpenLocalMapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;

public class HelpTakeInfoActivity extends BaseActivity<HelpTakeInfoPresenter, HelpTakeInfoModel> implements HelpTakeInfoContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helptake_price)
    TextView helptakePrice;
    @BindView(R.id.helptake_name)
    TextView helptakeName;
    @BindView(R.id.helptake_phone)
    ImageView helptakePhone;
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
    private String progress;
    private String order_id;
    private String spUserID;
    private String end_telephone;
    private long sid;
    private String end_lat;
    private String end_lng;
    private String end_address;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        String cate_id = intent.getStringExtra("cate_id");
        progress = intent.getStringExtra("progress");
        if (progress.equals("3")) {
            helptakeRight.setText("确认取件");
        } else if (progress.equals("8")) {
            helptakeRight.setText("确认送达");

        } else if (progress.equals("9")) {
            helptakeRight.setText("确认完成");
        } else {
            helptakeRight.setVisibility(View.GONE);
        }
        mMvpPresenter.orderInfo(order_id, cate_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helptake_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        actionBack.setOnClickListener(this);
        helptakeLeft.setOnClickListener(this);
        helptakeRight.setOnClickListener(this);
        helptakePhone.setOnClickListener(this);
        helptakeAddress.setOnClickListener(this);
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
            case R.id.helptake_left:
                //返回接单
                finish();
                break;
            case R.id.helptake_right:
                //判断类型 接单状态 /已接单状态
                if (progress.equals("3")) {
                    //确认取件接口
                    mMvpPresenter.confirmPurchase(order_id, spUserID, "", "0", mMultipleStateView);
                } else if (progress.equals("8")) {
                    //确认送达接口
                    mMvpPresenter.confirmService(order_id, mMultipleStateView);
                } else if (progress.equals("9")) {
                    //已完成接口
                    mMvpPresenter.confirmService(order_id, mMultipleStateView);
                }
                break;
            case R.id.helptake_phone:
                View dialog_iscall = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall, true, true, end_telephone);
                break;
            case R.id.helptake_address:
                 //收件地址 调取高德地图
                LatLng latLng = new LatLng(Double.parseDouble(end_lat), Double.parseDouble(end_lng));
                OpenLocalMapUtil.goToGaodeMap(this,latLng,end_address);
                break;
        }
    }

    @Override
    public void orderInfo(TakeOrderInfoBean bean) {
        //服务价格
        helptakePrice.setText(bean.getDetail().getPay_fee());
        //收件人姓名
        helptakeName.setText(bean.getDetail().getEnd_name());
        //收件人地址
        end_address = bean.getDetail().getEnd_address();
        helptakeAddress.setText(bean.getDetail().getEnd_address() + bean.getDetail().getEnd_detail());

        //物品类型
        helptakeType.setText(bean.getDetail().getType_name());
        //取件码
        helptakeCode.setText(bean.getDetail().getPickup_code());
        //快递点
        helptakeSendType.setText(bean.getDetail().getExpress_point());
        //配送时间
        helptakeTime.setText(bean.getDetail().getDelivery_time());
        //备注
        helptakeRemake.setText(bean.getDetail().getRemarks());
        //订单号
        helptakeCodeNumber.setText(bean.getDetail().getOrder_sn());
        //创建时间
        helptakeCreattime.setText(bean.getDetail().getCreate_time());
        //电话号码
        end_telephone = bean.getDetail().getEnd_telephone();
        //收件精度
        end_lat = bean.getDetail().getEnd_lat();
        //收件维度
        end_lng = bean.getDetail().getEnd_lng();
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
