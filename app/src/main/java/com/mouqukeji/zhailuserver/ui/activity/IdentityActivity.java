package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.IdentityBean;
import com.mouqukeji.zhailuserver.contract.activity.IdentityContract;
import com.mouqukeji.zhailuserver.model.activity.IdentityModel;
import com.mouqukeji.zhailuserver.presenter.activity.IdentityPresenter;
import com.mouqukeji.zhailuserver.utils.DateUtils;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.TokenHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IdentityActivity extends BaseActivity<IdentityPresenter, IdentityModel> implements IdentityContract.View, View.OnClickListener {


    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.dentity_name)
    EditText dentityName;
    @BindView(R.id.identity_school)
    EditText identitySchool;
    @BindView(R.id.identity_identity_number)
    EditText identityIdentityNumber;
    @BindView(R.id.identity_front_iv)
    ImageView identityFrontIv;
    @BindView(R.id.identity_front_tv)
    LinearLayout identityFrontTv;
    @BindView(R.id.identity_front)
    RelativeLayout identityFront;
    @BindView(R.id.identity_back_iv)
    ImageView identityBackIv;
    @BindView(R.id.identity_back_tv)
    LinearLayout identityBackTv;
    @BindView(R.id.identity_back)
    RelativeLayout identityBack;
    @BindView(R.id.identity_student_front_iv)
    ImageView identityStudentFrontIv;
    @BindView(R.id.identity_student_front_tv)
    LinearLayout identityStudentFrontTv;
    @BindView(R.id.identity_student_iv1)
    RelativeLayout identityStudentIv1;
    @BindView(R.id.identity_student_back_im)
    ImageView identityStudentBackIm;
    @BindView(R.id.identity_student_back_tv)
    LinearLayout identityStudentBackTv;
    @BindView(R.id.identity_student_iv2)
    RelativeLayout identityStudentIv2;
    @BindView(R.id.info_progress)
    LinearLayout infoProgress;
    @BindView(R.id.identity_bt)
    Button identityBt;
    private int type;
    List<LocalMedia> list = new ArrayList<>();
    private String spUserID;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_identity;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        //设置title
        actionTitle.setText("身份认证");
        initListener();
    }

    private void initListener() {
        //设置点击事件
        identityFront.setOnClickListener(this);
        identityBack.setOnClickListener(this);
        identityStudentIv1.setOnClickListener(this);
        identityStudentIv2.setOnClickListener(this);
        actionBack.setOnClickListener(this);
        identityBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.identity_front://身份证正面
                setPic();
                type = 1;
                break;
            case R.id.identity_back://身份证反面
                setPic();
                type = 2;
                break;
            case R.id.identity_student_iv1://学生证正面
                setPic();
                type = 3;
                break;
            case R.id.identity_student_iv2://学生证反面
                setPic();
                type = 4;
                break;
            case R.id.action_back:
                finish();
                break;
            case R.id.identity_bt:
                mMvpPresenter.identityCertification(spUserID,dentityName.getText().toString(),identitySchool.getText().toString(),identityIdentityNumber.getText().toString(),mMultipleStateView);
                break;
        }
    }

    private void setPic() {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    list = PictureSelector.obtainMultipleResult(data);
                    uploadImgSignQiNiu(list.get(0).getCompressPath());
                    infoProgress.setVisibility(View.VISIBLE);
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
                    checkType(url);//判断图片放置位置
                    infoProgress.setVisibility(View.GONE);
                } else {
                    Log.i("picture", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("picture", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }

    private void checkType(String url) {
        switch (type) {
            case 1:
                Glide.with(this).load(url).into(identityFrontIv);
                break;
            case 2:
                Glide.with(this).load(url).into(identityBackIv);
                break;
            case 3:
                Glide.with(this).load(url).into(identityStudentFrontIv);
                break;
            case 4:
                Glide.with(this).load(url).into(identityStudentBackIm);
                break;
        }
    }


    @Override
    public void identityCertification(IdentityBean bean) {
        Toast.makeText(this,"身份认证成功",Toast.LENGTH_SHORT).show();
    }


}
