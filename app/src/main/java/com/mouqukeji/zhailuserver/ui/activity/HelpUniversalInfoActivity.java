package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.model.LatLng;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.UniversalOrderInfoBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpUniversalInfoContract;
import com.mouqukeji.zhailuserver.model.activity.HelpUniversalInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpUniversalInfoPresenter;
import com.mouqukeji.zhailuserver.utils.DialogUtils;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.OpenLocalMapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;

public class HelpUniversalInfoActivity extends BaseActivity<HelpUniversalInfoPresenter, HelpUniversalInfoModel> implements HelpUniversalInfoContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helpuniversal_price)
    TextView helpuniversalPrice;
    @BindView(R.id.helpuniversal_name)
    TextView helpuniversalName;
    @BindView(R.id.helpuniversal_phone)
    ImageView helpuniversalPhone;
    @BindView(R.id.helpuniversal_address)
    TextView helpuniversalAddress;
    @BindView(R.id.helpuniversal_type)
    TextView helpuniversalType;
    @BindView(R.id.helpuniversal_time)
    TextView helpuniversalTime;
    @BindView(R.id.helpuniversal_code_number)
    TextView helpuniversalCodeNumber;
    @BindView(R.id.helpuniversal_creattime)
    TextView helpuniversalCreattime;
    @BindView(R.id.helpuniversal_left)
    Button helpuniversalLeft;
    @BindView(R.id.helpuniversal_right)
    Button helpuniversalRight;
    private String progress;
    private String order_id;
    private String spUserID;
    private String end_telephone;
    private String end_address;
    private String end_lng;
    private String end_lat;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        String cate_id = intent.getStringExtra("cate_id");
        progress = intent.getStringExtra("progress");
        if (progress.equals("3")) {
            helpuniversalRight.setText("确认出发");
        } else if (progress.equals("8")) {
            helpuniversalRight.setText("确认完成");
        } else {
            helpuniversalRight.setVisibility(View.GONE);
        }
        mMvpPresenter.universalOrderInfo(order_id, cate_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpuniversal_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        actionBack.setOnClickListener(this);
        helpuniversalLeft.setOnClickListener(this);
        helpuniversalRight.setOnClickListener(this);
        helpuniversalPhone.setOnClickListener(this);
        helpuniversalAddress.setOnClickListener(this);
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
            case R.id.helpuniversal_left:
                //返回接单
                finish();
                break;
            case R.id.helpuniversal_right:
                //判断类型 接单状态 /已接单状态
                if (progress.equals("3")) {
                    //确认取件接口
                    mMvpPresenter.confirmPurchase(order_id, spUserID, "", "0", mMultipleStateView);
                } else if (progress.equals("8")) {
                    //确认送达接口
                     mMvpPresenter.confirmService(order_id, mMultipleStateView);
                 }
                break;
            case R.id.helpuniversal_phone:
                View dialog_iscall = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall, true, true, end_telephone);
                break;
            case R.id.helpuniversal_address:
                LatLng latLng1 = new LatLng(Double.parseDouble(end_lat), Double.parseDouble(end_lng));
                OpenLocalMapUtil.goToGaodeMap(this,latLng1,end_address);
                break;
        }
    }


    @Override
    public void universalOrderInfo(UniversalOrderInfoBean bean) {
        //收经纬度
        end_lat = bean.getDetail().getEnd_lat();
        end_lng = bean.getDetail().getEnd_lng();
        end_address = bean.getDetail().getEnd_address();
        //服务价格
        helpuniversalPrice.setText(bean.getDetail().getPay_fee());
        //收件人姓名
        helpuniversalName.setText(bean.getDetail().getEnd_name());
        //收件人地址
        helpuniversalAddress.setText(bean.getDetail().getEnd_address() + bean.getDetail().getEnd_detail());
        //物品类型
        helpuniversalType.setText(bean.getDetail().getDemand());
        //配送时间
        helpuniversalTime.setText(bean.getDetail().getDelivery_time());
        //订单号
        helpuniversalCodeNumber.setText(bean.getDetail().getOrder_sn());
        //创建时间
        helpuniversalCreattime.setText(bean.getDetail().getCreate_time());
        //电话号码
        end_telephone = bean.getDetail().getEnd_telephone();
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
