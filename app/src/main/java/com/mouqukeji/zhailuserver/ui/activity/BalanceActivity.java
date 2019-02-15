package com.mouqukeji.zhailuserver.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.contract.activity.BalanceContract;
import com.mouqukeji.zhailuserver.model.activity.BalanceModel;
import com.mouqukeji.zhailuserver.presenter.activity.BalancePresenter;
import com.mouqukeji.zhailuserver.ui.fragment.AccountFragment;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;
import com.mouqukeji.zhailuserver.utils.DialogUtils;

import butterknife.BindView;

public class BalanceActivity extends BaseActivity<BalancePresenter, BalanceModel> implements BalanceContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.balance_select)
    ImageView balanceSelect;
    @BindView(R.id.balance_et)
    EditText balanceEt;
    @BindView(R.id.balance_bt)
    Button balanceBt;
    @BindView(R.id.balance_framelayout)
    FrameLayout balanceFramelayout;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_balance;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("余额提现");
        initListener();
    }

    private void initListener() {
        balanceSelect.setOnClickListener(this);
        balanceBt.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.balance_select:
                final CenterDialogView centerDialogView = DialogUtils.processDialog(BalanceActivity.this, getLayoutInflater().inflate(R.layout.dialog_account, null), true, true, balanceFramelayout);
                TextView dialogAccountAdd=centerDialogView.findViewById(R.id.dialog_account_add);
                dialogAccountAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerDialogView.dismiss();//关闭dialog
                        balanceFramelayout.setVisibility(View.VISIBLE);
                        //跳转账户添加页面
                        getSupportFragmentManager().beginTransaction().add(R.id.balance_framelayout, new AccountFragment(), "account").commit();
                    }
                });
                break;
            case R.id.balance_bt:

                break;
            case R.id.action_back:
                finish();
                break;
        }
    }

}
