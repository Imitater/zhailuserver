package com.mouqukeji.zhailuserver.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.EditPasswordBean;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.contract.activity.ChangePwContract;
import com.mouqukeji.zhailuserver.model.activity.ChangePwModel;
import com.mouqukeji.zhailuserver.presenter.activity.ChangePwPresenter;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import butterknife.BindView;

public class ChangePwActivity extends BaseActivity<ChangePwPresenter, ChangePwModel> implements ChangePwContract.View, View.OnClickListener {
    @BindView(R.id.imageButton)
    ImageView imageButton;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.button_change)
    Button buttonChange;
    private String oldPassword;
    private String spUserID;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                finish();
                break;
            case R.id.button_change:
                if (editText1.getText().toString().equals(editText2.getText().toString())) {
                    Toast.makeText(this, "新旧密码不能相同", Toast.LENGTH_SHORT).show();
                } else {
                    mMvpPresenter.editPassword(spUserID, editText1.getText().toString(), editText2.getText().toString(), mMultipleStateView);
                }
                break;
        }
    }

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_change_pw;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        initListener();
    }

    private void initListener() {
        imageButton.setOnClickListener(this);
        buttonChange.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void editPassword(EditPasswordBean bean) {
        Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
