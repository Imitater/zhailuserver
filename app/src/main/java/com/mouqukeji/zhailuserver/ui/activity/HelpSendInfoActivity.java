package com.mouqukeji.zhailuserver.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.SendOrderInfoBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpSendInfoContract;
import com.mouqukeji.zhailuserver.model.activity.HelpSendInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpSendInfoPresenter;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;
import com.mouqukeji.zhailuserver.ui.widget.FullDialogView;
import com.mouqukeji.zhailuserver.utils.DateUtils;
import com.mouqukeji.zhailuserver.utils.DialogUtils;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.OpenLocalMapUtil;
import com.mouqukeji.zhailuserver.utils.TokenHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;

public class HelpSendInfoActivity extends BaseActivity<HelpSendInfoPresenter, HelpSendInfoModel> implements HelpSendInfoContract.View, View.OnClickListener {

    List<LocalMedia> list = new ArrayList<>();
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helpsend_pricke)
    TextView helpsendPricke;
    @BindView(R.id.helpsend_top_name)
    TextView helpsendTopName;
    @BindView(R.id.helpsend_top_phone)
    ImageView helpsendTopPhone;
    @BindView(R.id.helpsend_top_address)
    TextView helpsendTopAddress;
    @BindView(R.id.helpsend_bottom_name)
    TextView helpsendBottomName;
    @BindView(R.id.helpsend_bottom_phone)
    ImageView helpsendBottomPhone;
    @BindView(R.id.helpsend_bottom_address)
    TextView helpsendBottomAddress;
    @BindView(R.id.helpsend_type)
    TextView helpsendType;
    @BindView(R.id.helpsend_payway)
    TextView helpsendPayway;
    @BindView(R.id.helpsend_deliver_price)
    TextView helpsendDeliverPrice;
    @BindView(R.id.helpsend_deliver_check)
    TextView helpsendDeliverCheck;
    @BindView(R.id.helpsend_point)
    TextView helpsendPoint;
    @BindView(R.id.helpsend_time)
    TextView helpsendTime;
    @BindView(R.id.helpsend_remake)
    TextView helpsendRemake;
    @BindView(R.id.helpsend_code_number)
    TextView helpsendCodeNumber;
    @BindView(R.id.helpsend_creattime)
    TextView helpsendCreattime;
    @BindView(R.id.helpsend_left)
    Button helpsendLeft;
    @BindView(R.id.helpsend_right)
    Button helpsendRight;
    @BindView(R.id.helpsend_send_price)
    TextView helpsendSendPrice;
    @BindView(R.id.helpsend_send_price_ll)
    LinearLayout helpsendSendPriceLl;
    private String progress;
    private String spUserID;
    private String order_id;
    private String end_telephone;
    private String start_telephone;
    private String url;
    private String money;
    private String makeup_fee;
    private String picture;
    private String express_pay_type;
    private ImageView dialogSendIv;
    private ImageView dialogIv;
    private FullDialogView fullDialogView;
    private String start_lat;
    private String start_lng;
    private String start_address;


    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        spUserID = new GetSPData().getSPUserID(this);
        order_id = intent.getStringExtra("order_id");
        String cate_id = intent.getStringExtra("cate_id");
        progress = intent.getStringExtra("progress");
        if (progress.equals("3")) {
            helpsendDeliverCheck.setVisibility(View.GONE);
            helpsendRight.setText("确认取件");
        } else if (progress.equals("8")) {
            helpsendDeliverCheck.setVisibility(View.GONE);
            helpsendRight.setText("确认寄出");
        } else if (progress.equals("9")) {
            helpsendDeliverCheck.setVisibility(View.VISIBLE);
            helpsendRight.setText("确认完成");
        } else {
            helpsendRight.setVisibility(View.GONE);
        }
        mMvpPresenter.sendfOrderInfo(order_id, cate_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpsend_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        actionBack.setOnClickListener(this);
        helpsendRight.setOnClickListener(this);
        helpsendLeft.setOnClickListener(this);
        helpsendTopPhone.setOnClickListener(this);
        helpsendBottomPhone.setOnClickListener(this);
        helpsendDeliverCheck.setOnClickListener(this);
        helpsendTopAddress.setOnClickListener(this);
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
            case R.id.helpsend_left:
                //返回接单
                finish();
                break;
            case R.id.helpsend_right:
                //判断状态 接单/未接
                //判断类型 接单状态 /已接单状态
                if (progress.equals("3")) {
                    //确认取件接口
                    mMvpPresenter.confirmPurchase(order_id, spUserID, "", "0", mMultipleStateView);
                } else if (progress.equals("8")) {
                    //判断是否到付
                    if (express_pay_type.equals("1")) {
                        //寄付  支付接口
                        ItemsPriceSendDialog(HelpSendInfoActivity.this, getLayoutInflater().inflate(R.layout.dialog_helpbuy, null), true, true);
                    } else {
                        //确认送达接口
                        mMvpPresenter.confirmService(order_id, mMultipleStateView);
                    }
                } else if (progress.equals("9")) {
                    //已完成接口
                    mMvpPresenter.confirmFinish(order_id, mMultipleStateView);
                }
                break;
            case R.id.helpsend_top_phone:
                View dialog_iscall = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall, true, true, end_telephone);
                break;
            case R.id.helpsend_bottom_phone:
                View dialog_iscall1 = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall1, true, true, start_telephone);
                break;
            case R.id.helpsend_deliver_check:
                //显示凭证
                check();
                break;
            case R.id.helpsend_top_address:
                LatLng latLng1 = new LatLng(Double.parseDouble(start_lat), Double.parseDouble(start_lng));
                OpenLocalMapUtil.goToGaodeMap(this,latLng1,start_address);
                break;
        }
    }

    //查看框
    public void check() {
        View inflate_check = getLayoutInflater().inflate(R.layout.dialog_buy_check, null);
        final CenterDialogView centerDialogView = DialogUtils.checkDialog(HelpSendInfoActivity.this, HelpSendInfoActivity.this, inflate_check, true, true, picture);
        ImageView dialogIv = centerDialogView.findViewById(R.id.dialog_iv);
        TextView dialogcheckMoney = centerDialogView.findViewById(R.id.dialog_check_money);
        TextView dialogCheckLeftBt = centerDialogView.findViewById(R.id.dialog_check_left_bt);

        Glide.with(HelpSendInfoActivity.this).load(picture).into(dialogIv);
        dialogcheckMoney.setText(makeup_fee);
        //返回菜单按键
        dialogCheckLeftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDialogView.dismiss();
            }
        });
    }

    //商品价格框
    public void ItemsPriceSendDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable) {
        final CenterDialogView centerDialogView = new CenterDialogView(activity, view, isCancelable, isBackCancelable);
        centerDialogView.show();
        LinearLayout dialogBuyInfoPic = centerDialogView.findViewById(R.id.dialog_buy_info_pic);
        final EditText dialogBuyInfoEt = centerDialogView.findViewById(R.id.dialog_buy_info_et);
        Button dialogBuyInfoBt = centerDialogView.findViewById(R.id.dialog_buy_info_bt);
        ImageView dialogBuyInfoClose = centerDialogView.findViewById(R.id.dialog_buy_info_close);
        dialogSendIv = centerDialogView.findViewById(R.id.dialog_iv);
        dialogBuyInfoEt.setText(makeup_fee);
        dialogBuyInfoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDialogView.dismiss();
            }
        });
        dialogBuyInfoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putcheck();
            }
        });
        dialogBuyInfoBt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                money = dialogBuyInfoEt.getText().toString();
                //确认取件接口
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(HelpSendInfoActivity.this, "请上传凭证图片", Toast.LENGTH_SHORT).show();
                } else if (money.equals("-1")) {
                    Toast.makeText(HelpSendInfoActivity.this, "请输入商品价格", Toast.LENGTH_SHORT).show();
                } else {
                    mMvpPresenter.sendOrder(order_id, spUserID, url, money, mMultipleStateView);
                }
                centerDialogView.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    list = PictureSelector.obtainMultipleResult(data);
                    uploadImgSignQiNiu(list.get(0).getCompressPath());
                    fullDialogView = DialogUtils.loadingDialog(this, getLayoutInflater().inflate(R.layout.dialog_loading, null), false, false);
                    break;
            }
        }
    }

    //上传图片到九牛
    public void uploadImgSignQiNiu(final String path) {
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        String key = "icon_" + num + DateUtils.getData();
        TokenHelper tokenHelper = TokenHelper.create("Nwz4XdKR-G777FoMf-DrjaySeCWvjiwv7gd4sIm1", "aZkyjMBELmPthFf-60rwJQKR0eXYazHydDG8uF4H");
        String token = tokenHelper.getToken("mouqukeji");
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(path, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res包含hash、key等信息，具体字段取决于上传策略的设置
                if (info.isOK()) {
                    url = "http://picture.mouqukeji.com/" + key;
                    Glide.with(HelpSendInfoActivity.this).load(url).into(dialogIv);
                    Glide.with(HelpSendInfoActivity.this).load(url).into(dialogSendIv);
                    fullDialogView.dismiss();
                } else {
                    Log.i("picture", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("picture", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }

    private void putcheck() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .cropCompressQuality(50)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMedia(list)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }




    @Override
    public void sendOrderInfo(SendOrderInfoBean bean) {
        //寄经纬度
        start_lat = bean.getDetail().getStart_lat();
        start_lng = bean.getDetail().getStart_lng();
        start_address = bean.getDetail().getStart_address();
        //服务价格
        helpsendPricke.setText(bean.getDetail().getPay_fee());
        //取件人姓名
        helpsendBottomName.setText(bean.getDetail().getName());
        //取件人地址
        helpsendBottomAddress.setText(bean.getDetail().getAddress() + bean.getDetail().getDetail());
        //收件人姓名
        helpsendTopName.setText(bean.getDetail().getStart_name());
        //收件人地址
        helpsendTopAddress.setText(bean.getDetail().getStart_address() + bean.getDetail().getStart_detail());
        //物品类型
        helpsendType.setText(bean.getDetail().getType_name());
        //揽件时间
        helpsendTime.setText(bean.getDetail().getDelivery_time());
        //邮费价格
        helpsendDeliverPrice.setText(bean.getDetail().getMakeup_fee());
        //付款方式
        helpsendPayway.setText(bean.getDetail().getExpress_pay_type());
        //快递点
        helpsendPoint.setText(bean.getDetail().getExpress_point());
        //商品价格
        makeup_fee = bean.getDetail().getMakeup_fee();
        //图片地址
        picture = bean.getDetail().getPicture();
        //备注
        helpsendRemake.setText(bean.getDetail().getRemarks());
        //订单号
        helpsendCodeNumber.setText(bean.getDetail().getOrder_sn());
        //创建时间
        helpsendCreattime.setText(bean.getDetail().getCreate_time());
        //取件人电话
        end_telephone = bean.getDetail().getStart_telephone();
        //收件人电话
        start_telephone = bean.getDetail().getStart_telephone();
        express_pay_type = bean.getDetail().getExpress_pay_type();
        if (makeup_fee.equals("0.00")) {
            helpsendSendPrice.setVisibility(View.GONE);
            helpsendSendPriceLl.setVisibility(View.GONE);
        } else {
            helpsendSendPrice.setVisibility(View.VISIBLE);
            helpsendSendPriceLl.setVisibility(View.VISIBLE);
            helpsendDeliverPrice.setText(makeup_fee);
        }
        //付款方式
        if (bean.getDetail().getExpress_pay_type().equals("1")) {
            helpsendPayway.setText("快递到付");
        } else {
            helpsendPayway.setText("寄付现结");
        }
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

    @Override
    public void sendOrder(SendOrderBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
    }


}
