package com.mouqukeji.zhailuserver.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.HelpBuyInfoContract;
import com.mouqukeji.zhailuserver.contract.activity.HelpSendInfoContract;
import com.mouqukeji.zhailuserver.model.activity.HelpBuyInfoModel;
import com.mouqukeji.zhailuserver.model.activity.HelpSendInfoModel;
import com.mouqukeji.zhailuserver.presenter.activity.HelpBuyInfoPresenter;
import com.mouqukeji.zhailuserver.presenter.activity.HelpSendInfoPresenter;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;
import com.mouqukeji.zhailuserver.utils.DateUtils;
import com.mouqukeji.zhailuserver.utils.TokenHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpSendInfoActivity extends BaseActivity<HelpSendInfoPresenter, HelpSendInfoModel> implements HelpSendInfoContract.View, View.OnClickListener {

    List<LocalMedia> list = new ArrayList<>();
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.helptake_type)
    TextView helptakeType;
    @BindView(R.id.helptake_code)
    TextView helptakeCode;
    @BindView(R.id.helpbuy_check)
    TextView helpbuyCheck;
    @BindView(R.id.helptake_send_type)
    TextView helptakeSendType;
    @BindView(R.id.helptake_time)
    TextView helptakeTime;
    @BindView(R.id.helptake_sex)
    TextView helptakeSex;
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

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpsend_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        actionBack.setOnClickListener(this);
        helptakeRight.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
        putcheck();
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
            case R.id.helptake_right:
                ItemsPriceDialog(HelpSendInfoActivity.this, getLayoutInflater().inflate(R.layout.dialog_helpbuy, null), true, true);
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
                    String url = "http://picture.mouqukeji.com/" + key;
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
        EditText dialogBuyInfoEt = centerDialogView.findViewById(R.id.dialog_buy_info_et);
        Button dialogBuyInfoBt = centerDialogView.findViewById(R.id.dialog_buy_info_bt);
        ImageView dialogBuyInfoClose = centerDialogView.findViewById(R.id.dialog_buy_info_close);

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
                centerDialogView.dismiss();
            }
        });
    }


}
