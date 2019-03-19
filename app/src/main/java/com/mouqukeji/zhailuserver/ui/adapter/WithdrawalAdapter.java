package com.mouqukeji.zhailuserver.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.bean.PaymentDetailsBean;

import java.util.List;

public class WithdrawalAdapter extends BaseQuickAdapter <PaymentDetailsBean.ServerBillListBean, BaseViewHolder>{
    public WithdrawalAdapter(int layoutResId, @Nullable List<PaymentDetailsBean.ServerBillListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PaymentDetailsBean.ServerBillListBean item) {
        if (item.getType().equals("1")){
            helper.setText(R.id.adapter_type,"跑腿订单收入");
            helper.setBackgroundRes(R.id.adapter_icon,R.mipmap.mipmap_get);
        }else if (item.getType().equals("2")){
            helper.setText(R.id.adapter_type,"二次支付收入");
            helper.setBackgroundRes(R.id.adapter_icon,R.mipmap.mipmap_get);
        }else{
            helper.setText(R.id.adapter_type,"提现");
            helper.setBackgroundRes(R.id.adapter_icon,R.mipmap.mipmap_money);
        }
        //时间
        helper.setText(R.id.adapter_time,item.getCreate_time());
        helper.setText(R.id.adapter_money,"+"+item.getMoney());
    }

}
