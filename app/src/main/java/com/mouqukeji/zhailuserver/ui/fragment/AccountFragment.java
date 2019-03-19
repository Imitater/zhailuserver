package com.mouqukeji.zhailuserver.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.bean.AddAccountBean;
import com.mouqukeji.zhailuserver.contract.fragment.AccountContract;
import com.mouqukeji.zhailuserver.model.fragment.AccountModel;
import com.mouqukeji.zhailuserver.presenter.fragment.AccountPresenter;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import butterknife.BindView;

import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;

public class AccountFragment extends BaseFragment<AccountPresenter, AccountModel> implements AccountContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.account_name)
    EditText accountName;
    @BindView(R.id.account_number)
    EditText accountNumber;
    @BindView(R.id.account_bt)
    Button accountBt;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_account;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("支付宝账户");
        initListener();
        initBackListener();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        accountBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_back:
                setBack();
                break;
            case R.id.account_bt:
                if (TextUtils.isEmpty(accountName.getText().toString())){
                    Toast.makeText(getContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(accountNumber.getText().toString())){
                    Toast.makeText(getContext(),"账号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    mMvpPresenter.addAccount("1", spUserID, accountName.getText().toString(), accountNumber.getText().toString(), mMultipleStateView);
                }
                break;
        }
    }

    @Override
    public void addAccount(AddAccountBean bean) {
        Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_A, 1);
        post(eventMessage);
        setBack();
    }

    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment add = fragmentManager.findFragmentByTag("account");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(add);
        fragmentTransaction.commit();
    }

    private void initBackListener() {
        //返回键监听
        getContentView().setFocusableInTouchMode(true);
        getContentView().requestFocus();
        getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    setBack();
                    return true;
                }
                return false;
            }
        });
    }

}
