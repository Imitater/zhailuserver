package com.mouqukeji.zhailuserver.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.AccountListBean;
import com.mouqukeji.zhailuserver.bean.DeleteAccountBean;
import com.mouqukeji.zhailuserver.contract.activity.WithdrawalAContract;
import com.mouqukeji.zhailuserver.model.activity.WithdrawalAModel;
import com.mouqukeji.zhailuserver.presenter.activity.WithdrawalAPresenter;
import com.mouqukeji.zhailuserver.ui.fragment.AccountFragment;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;

import butterknife.BindView;

public class WithdrawalActivity extends BaseActivity<WithdrawalAPresenter, WithdrawalAModel> implements WithdrawalAContract.View, View.OnClickListener {


    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.withdrawal_next)
    ImageView withdrawalNext;
    @BindView(R.id.withdrawal_show)
    LinearLayout withdrawalShow;
    @BindView(R.id.withdrawal_line)
    View withdrawalLine;
    @BindView(R.id.withdrawal_item)
    LinearLayout withdrawalItem;
    @BindView(R.id.withdrawal_bt)
    Button withdrawalBt;
    @BindView(R.id.withdrawal_framelayout)
    FrameLayout withdrawalFramelayout;
    @BindView(R.id.withdrawal_account_number)
    TextView withdrawalAccountNumber;
    @BindView(R.id.withdrawal_type)
    TextView withdrawalType;
    @BindView(R.id.withdrawal_relativelatyout)
    RelativeLayout withdrawalRelativelatyout;
    private AccountListBean bean1;
    private String spUserID;
    private boolean flag=false;
    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getAcountList(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("提现账户");
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        withdrawalBt.setOnClickListener(this);
        withdrawalType.setOnClickListener(this);
        withdrawalShow.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.withdrawal_bt:
                withdrawalFramelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.withdrawal_framelayout, new AccountFragment(), "account").commit();
                break;
            case R.id.withdrawal_type://删除
                //删除账号接口
                mMvpPresenter.deleteAccount(bean1.getAccountInfo().getId(), spUserID, mMultipleStateView);
                break;
            case R.id.withdrawal_show://右侧箭头按钮
                if (!flag) {
                    withdrawalLine.setVisibility(View.VISIBLE);
                    withdrawalRelativelatyout.setVisibility(View.VISIBLE);
                    flag=true;
                    withdrawalNext.setBackgroundResource(R.mipmap.mipmap_down);
                }else{
                    withdrawalLine.setVisibility(View.GONE);
                    withdrawalRelativelatyout.setVisibility(View.GONE);
                    flag=false;
                    withdrawalNext.setBackgroundResource(R.mipmap.mipmap_next);
                }
                break;
        }
    }

    @Override
    public void getAcountList(AccountListBean bean) {
        bean1 = bean;
        withdrawalItem.setVisibility(View.VISIBLE);
        withdrawalBt.setVisibility(View.GONE);
        withdrawalAccountNumber.setText(bean.getAccountInfo().getName() + " " + bean.getAccountInfo().getAccount());
    }

    @Override
    public void deleteAccount(DeleteAccountBean bean) {
        mMvpPresenter.getAcountList(spUserID,mMultipleStateView);
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isEmpty() {
        //账号列表为空
        withdrawalItem.setVisibility(View.GONE);
        withdrawalBt.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
           mMvpPresenter.getAcountList(spUserID,mMultipleStateView);
        }
    }

}
