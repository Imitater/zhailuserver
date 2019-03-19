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
import com.mouqukeji.zhailuserver.bean.BuyOrderInfoBean;
import com.mouqukeji.zhailuserver.bean.ConfirmFinishBean;
import com.mouqukeji.zhailuserver.bean.ConfirmGetBuyBean;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.contract.activity.HelpBuyInfoContract;
import com.mouqukeji.zhailuserver.model.activity.HelpBuyInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpBuyInfoPresenter;
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

public class HelpBuyInfoActivity extends BaseActivity<HelpBuyInfoPresenter, HelpBuyInfoModel> implements HelpBuyInfoContract.View, View.OnClickListener {

    List<LocalMedia> list = new ArrayList<>();
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helpbuy_price)
    TextView helpbuyPrice;
    @BindView(R.id.helpbuy_name)
    TextView helpbuyName;
    @BindView(R.id.helpbuy_phone)
    ImageView helpbuyPhone;
    @BindView(R.id.helpbuy_address)
    TextView helpbuyAddress;
    @BindView(R.id.helpbuy_type)
    TextView helpbuyType;
    @BindView(R.id.helpbuy_code)
    TextView helpbuyCode;
    @BindView(R.id.helpbuy_check)
    TextView helpbuyCheck;
    @BindView(R.id.helpbuy_send_type)
    TextView helpbuySendType;
    @BindView(R.id.helpbuy_time)
    TextView helpbuyTime;
    @BindView(R.id.helpbuy_remake)
    TextView helpbuyRemake;
    @BindView(R.id.helptake_code_number)
    TextView helptakeCodeNumber;
    @BindView(R.id.helptake_creattime)
    TextView helptakeCreattime;
    @BindView(R.id.helpbuy_left)
    Button helpbuyLeft;
    @BindView(R.id.helpbuy_right)
    Button helpbuyRight;
    @BindView(R.id.info_progress)
    LinearLayout infoProgress;
    @BindView(R.id.helpbuy_type_money)
    TextView helpbuyTypeMoney;
    @BindView(R.id.helpbuy_type_money_ll)
    LinearLayout helpbuyTypeMoneyLl;
    private String progress;
    private String order_id;
    private String spUserID;
    private String money = "-1";
    private String url = "";
    private String end_telephone;
    private long sid;
    private String picture;
    private String makeup_fee;
    private ImageView dialogIv;
    private FullDialogView fullDialogView;
    private String buy_address;
    private String buy_lat;
    private String end_address;
    private String end_lat;
    private String end_lng;
    private String buy_lng;


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpbuy_info;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        String cate_id = intent.getStringExtra("cate_id");
        progress = intent.getStringExtra("progress");
        if (progress.equals("3")) {
            helpbuyRight.setText("确认购买");
            helpbuyCheck.setVisibility(View.GONE);
        } else if (progress.equals("8")) {
            helpbuyCheck.setVisibility(View.GONE);
            helpbuyRight.setText("确认送达");
        } else if (progress.equals("9")) {
            helpbuyRight.setText("确认完成");
            helpbuyCheck.setVisibility(View.VISIBLE);
        } else {
            helpbuyRight.setVisibility(View.GONE);
        }
        mMvpPresenter.buyOrderInfo(order_id, cate_id, mMultipleStateView);
        actionTitle.setText("订单详情");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        helpbuyRight.setOnClickListener(this);
        helpbuyLeft.setOnClickListener(this);
        helpbuyCheck.setOnClickListener(this);
        helpbuyPhone.setOnClickListener(this);
        helpbuyAddress.setOnClickListener(this);
        helpbuySendType.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.helpbuy_check:
                check();
                 break;
            case R.id.helpbuy_left:
                //返回接单
                finish();
                break;
            case R.id.helpbuy_right:
                //判断类型 已接单/未接单
                if (progress.equals("3")) {
                    ItemsPriceDialog(HelpBuyInfoActivity.this, getLayoutInflater().inflate(R.layout.dialog_helpbuy, null), true, true);
                } else if (progress.equals("8")) {
                    //确认送达接口
                    mMvpPresenter.confirmService(order_id, mMultipleStateView);
                } else if (progress.equals("9")) {
                    //已完成接口
                    mMvpPresenter.confirmFinish(order_id, mMultipleStateView);
                }
                break;
            case R.id.helpbuy_phone:
                View dialog_iscall = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(this, dialog_iscall, true, true, end_telephone);
                break;
            case R.id.helpbuy_address:
                //收件地址 调取高德地图
                LatLng latLng = new LatLng(Double.parseDouble(end_lat), Double.parseDouble(end_lng));
                OpenLocalMapUtil.goToGaodeMap(this,latLng,end_address);
                break;
            case R.id.helpbuy_send_type:
                //购买地址
                if (!helpbuySendType.getText().toString().equals("就近购买")){
                    helpbuySendType.setTextColor(getResources().getColor(R.color.blue));
                    LatLng latLng1 = new LatLng(Double.parseDouble(buy_lat), Double.parseDouble(buy_lng));
                    OpenLocalMapUtil.goToGaodeMap(this,latLng1,buy_address);
                }else{
                    helpbuySendType.setTextColor(getResources().getColor(R.color.black));
                }

                break;
        }
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
                    Glide.with(HelpBuyInfoActivity.this).load(url).into(dialogIv);
                    fullDialogView.dismiss();
                } else {
                    Log.i("picture", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("picture", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }


    //商品价格框
    public void ItemsPriceDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable) {
        final CenterDialogView centerDialogView = new CenterDialogView(activity, view, isCancelable, isBackCancelable);
        centerDialogView.show();
        LinearLayout dialogBuyInfoPic = centerDialogView.findViewById(R.id.dialog_buy_info_pic);
        final EditText dialogBuyInfoEt = centerDialogView.findViewById(R.id.dialog_buy_info_et);
        Button dialogBuyInfoBt = centerDialogView.findViewById(R.id.dialog_buy_info_bt);
        ImageView dialogBuyInfoClose = centerDialogView.findViewById(R.id.dialog_buy_info_close);
        dialogIv = centerDialogView.findViewById(R.id.dialog_iv);
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
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(HelpBuyInfoActivity.this, "请上传凭证图片", Toast.LENGTH_SHORT).show();
                } else if (money.equals("-1")) {
                    Toast.makeText(HelpBuyInfoActivity.this, "请输入商品价格", Toast.LENGTH_SHORT).show();
                } else {
                    mMvpPresenter.confirmPurchase(order_id, spUserID, url, money, mMultipleStateView);
                }
                centerDialogView.dismiss();
            }
        });
    }

    //查看框
    public void check() {
        View inflate_check = getLayoutInflater().inflate(R.layout.dialog_buy_check, null);
        final CenterDialogView centerDialogView = DialogUtils.checkDialog(HelpBuyInfoActivity.this, HelpBuyInfoActivity.this, inflate_check, true, true, picture);
        ImageView dialogIv = centerDialogView.findViewById(R.id.dialog_iv);
        TextView dialogcheckMoney = centerDialogView.findViewById(R.id.dialog_check_money);
        TextView dialogCheckLeftBt = centerDialogView.findViewById(R.id.dialog_check_left_bt);

        Glide.with(HelpBuyInfoActivity.this).load(picture).into(dialogIv);
        dialogcheckMoney.setText(makeup_fee);
        //返回菜单按键
        dialogCheckLeftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDialogView.dismiss();
            }
        });
    }

    @Override
    public void buyOrderInfo(BuyOrderInfoBean bean) {
        //购买地址
        buy_address = bean.getDetail().getBuy_address();
        //购买精度
        buy_lat = bean.getDetail().getBuy_lat();
        //购买维度
        buy_lng = bean.getDetail().getBuy_lng();
        //收件地址
        end_address = bean.getDetail().getEnd_address();
        //收件精度
        end_lat = bean.getDetail().getEnd_lat();
        //收件维度
        end_lng = bean.getDetail().getEnd_lng();
        //服务价格
        helpbuyPrice.setText(bean.getDetail().getPay_fee());
        //收件人姓名
        helpbuyName.setText(bean.getDetail().getEnd_name());
        //收件人地址
        helpbuyAddress.setText(bean.getDetail().getEnd_address() + bean.getDetail().getEnd_detail());
        //购买商品
        helpbuyType.setText(bean.getDetail().getGoods());
        //配送时间
        helpbuyTime.setText(bean.getDetail().getDelivery_time());
        //备注
        helpbuyRemake.setText(bean.getDetail().getRemarks());
        //商品价格
        helpbuyCode.setText(bean.getDetail().getMakeup_fee());
        //订单号
        helptakeCodeNumber.setText(bean.getDetail().getOrder_sn());
        //创建时间
        helptakeCreattime.setText(bean.getDetail().getCreate_time());
        //电话号码
        end_telephone = bean.getDetail().getEnd_telephone();
        //图片路径
        picture = bean.getDetail().getPicture();
        //商品价格
        makeup_fee = bean.getDetail().getMakeup_fee();
        //购买地址
        if (TextUtils.isEmpty(bean.getDetail().getBuy_address())) {
            helpbuySendType.setText("就近购买");
        } else {
            helpbuySendType.setText(bean.getDetail().getBuy_address());
        }
        if (makeup_fee.equals("0.00")) {
            helpbuyTypeMoney.setVisibility(View.GONE);
            helpbuyTypeMoneyLl.setVisibility(View.GONE);
        } else {
            helpbuyTypeMoney.setVisibility(View.VISIBLE);
            helpbuyTypeMoneyLl.setVisibility(View.VISIBLE);
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

}
