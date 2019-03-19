package com.mouqukeji.zhailuserver.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.ResetPasswordBean;
import com.mouqukeji.zhailuserver.contract.activity.GetbackPw3Contract;
import com.mouqukeji.zhailuserver.model.activity.GetbackPw3Model;
import com.mouqukeji.zhailuserver.presenter.activity.GetbackPw3Presenter;
import com.mouqukeji.zhailuserver.utils.CodeUtil;
import com.mouqukeji.zhailuserver.utils.LoginQuit;

import butterknife.BindView;

public class GetbackPw3Activity extends BaseActivity<GetbackPw3Presenter, GetbackPw3Model> implements GetbackPw3Contract.View, View.OnClickListener {

    @BindView(R.id.reset_pass)
    Button resetPass;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.button_sign_in)
    Button buttonSignIn;
    private String telephone;
    private String code;
    private String password;


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_getback_pw3;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();//获取传递的参数
        telephone = intent.getStringExtra("telephone");//获取number
        code = intent.getStringExtra("code");//获取code

        //设置点击事件
        setListener();
    }

    @Override
    protected void setUpData() {

    }


    public void setListener() {
        buttonSignIn.setOnClickListener(this);
        resetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        int id = v.getId();
        switch (id) {
            case R.id.button_sign_in:
                password = editText1.getText().toString();
                if (CodeUtil.isPassword(password)) {
                    //重置密码
                    mMvpPresenter.resetPassword(GetbackPw3Activity.this, telephone, code, password, mMultipleStateView);
                } else {
                    Toast.makeText(GetbackPw3Activity.this, "请输入6-16位字母数字混合密码,首位不为数字", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reset_pass:
                finish();
                break;
        }
    }


    @Override
    public void resetPassword(ResetPasswordBean bean) {
        Toast.makeText(GetbackPw3Activity.this, "密码重置成功", Toast.LENGTH_SHORT).show();
        new LoginQuit().loginQuit(GetbackPw3Activity.this, 0);
    }



}
