package com.mouqukeji.zhailuserver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.contract.activity.AboutContract;
import com.mouqukeji.zhailuserver.model.activity.AboutModel;
import com.mouqukeji.zhailuserver.presenter.activity.AboutPresenter;
import com.mouqukeji.zhailuserver.update.ICheckAgent;
import com.mouqukeji.zhailuserver.update.IUpdateChecker;
import com.mouqukeji.zhailuserver.update.IUpdateParser;
import com.mouqukeji.zhailuserver.update.UpdateInfo;
import com.mouqukeji.zhailuserver.update.UpdateManager;
import com.mouqukeji.zhailuserver.utils.PhoneUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity<AboutPresenter, AboutModel> implements AboutContract.View, View.OnClickListener {

    @BindView(R.id.about_versiom)
    LinearLayout aboutVersiom;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_about;
    }

    @Override
    protected void setUpView() {
        setListener();
        //设置title
        actionTitle.setText("关于宅鹿");
    }

    private void setListener() {
        aboutVersiom.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_versiom:
                mMvpPresenter.checkVersion(this,"android",PhoneUtils.getVersionName(this),"2",mMultipleStateView);
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }


    @Override
    public void checkVersion(CheckVersionBean backBean) {
        if (backBean.getVersionInfo().getVersion_code().equals(PhoneUtils.getVersionName(this))){
            Toast.makeText(this,"当前已是最新版",Toast.LENGTH_SHORT).show();
        }else{
            check(false, true, false,
                    false, 998,backBean.getVersionInfo().getApk_md5(),backBean.getVersionInfo().getApk_size(),backBean.getVersionInfo().getApk_url(),
                    backBean.getVersionInfo().getVersion_code(),backBean.getVersionInfo().getUpdate_info(),backBean.getVersionInfo().getIs_compel());
        }
    }


    void check(boolean isManual, final boolean hasUpdate, final boolean isSilent,
               final boolean isIgnorable, final int notifyId, final String apk_md5,final String size,final String url,final String versionName,final String upInfo
            ,final String isForce) {
        UpdateManager.create(this).setChecker(new IUpdateChecker() {
            @Override
            public void check(ICheckAgent agent, String url) {
                agent.setInfo("");
            }
        }).setWifiOnly(true).setUrl("https://api.hmdeer.com/deer/Login/checkVersion?platform=android&version_code="+versionName+"&type=2").setManual(isManual)
                .setNotifyId(notifyId).setParser(new IUpdateParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = hasUpdate;
                info.updateContent =upInfo;
                info.versionCode = 1;
                info.versionName = versionName;
                info.url = url;
                info.md5 = apk_md5;
                if (isForce.equals("0")){
                    info.isForce = false;
                }else{
                    info.isForce = true;
                }
                info.size=Long.parseLong(size);
                info.isIgnorable = isIgnorable;
                info.isSilent = isSilent;
                return info;
            }
        }).check();
    }

    @Override
    public void isNeedUp() {
        Toast.makeText(this,"当前已是最新版",Toast.LENGTH_SHORT).show();
    }
}
