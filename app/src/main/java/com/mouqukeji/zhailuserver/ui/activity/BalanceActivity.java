package com.mouqukeji.zhailuserver.ui.activity;

import android.text.TextUtils;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.YueWithdrawBean;
import com.mouqukeji.zhailuserver.contract.activity.BalanceContract;
import com.mouqukeji.zhailuserver.model.activity.BalanceModel;
import com.mouqukeji.zhailuserver.presenter.activity.BalancePresenter;
import com.mouqukeji.zhailuserver.ui.fragment.AccountFragment;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;
import com.mouqukeji.zhailuserver.utils.DialogUtils;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;

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
    private TextView dialogAccountAdd;
    private String spUserID;
    private int type=0;
    private String account;
    private String id;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getAcountList(spUserID, mMultipleStateView);
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
                dialogAccountAdd = centerDialogView.findViewById(R.id.dialog_account_add);
                if (type==1){
                    dialogAccountAdd.setText("支付宝("+account+")");
                }else if (type==-1){
                    dialogAccountAdd.setText("添加支付宝提现账号");
                }
                dialogAccountAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type==1){
                            centerDialogView.dismiss();//关闭dialog
                        }else if (type==-1){
                            balanceFramelayout.setVisibility(View.VISIBLE);
                            //跳转账户添加页面
                            getSupportFragmentManager().beginTransaction().add(R.id.balance_framelayout, new AccountFragment(), "account").commit();
                            centerDialogView.dismiss();//关闭dialog
                        }

                    }
                });
                break;
            case R.id.balance_bt:
                if (TextUtils.isEmpty(balanceEt.getText().toString())) {
                    Toast.makeText(this,"金额不能为空",Toast.LENGTH_SHORT).show();
                }else if (balanceEt.getText().toString().equals("0")){
                    Toast.makeText(this,"金额不能为0",Toast.LENGTH_SHORT).show();
                }else{
                    mMvpPresenter.yueWithdraw(spUserID, balanceEt.getText().toString(), id, mMultipleStateView);
                }
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mMvpPresenter.getAcountList(spUserID, mMultipleStateView);
    }

    @Override
    public void getAcountList(AccountListBean bean) {
        type=1;
        account = bean.getAccountInfo().getAccount();
        id = bean.getAccountInfo().getId();
    }

    @Override
    public void isEmpty() {
        type=-1;
    }

    @Override
    public void yueWithdraw(YueWithdrawBean bean) {
        Toast.makeText(this,"余额提现成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isEnoughMoney() {
        Toast.makeText(this,"余额不足",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode()== EventCode.EVENT_A) {
                mMvpPresenter.getAcountList(spUserID, mMultipleStateView);
            }
        }
    }
}
