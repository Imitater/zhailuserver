package com.mouqukeji.zhailuserver.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.contract.fragment.AccountContract;
import com.mouqukeji.zhailuserver.model.fragment.AccountModel;
import com.mouqukeji.zhailuserver.presenter.fragment.AccountPresenter;

import butterknife.BindView;

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

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_account;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("支付宝账户");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_back:
                FragmentManager fragmentManager = getFragmentManager();
                Fragment add = fragmentManager.findFragmentByTag("account");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(add);
                fragmentTransaction.commit();
                break;
        }
    }
}
