package com.mouqukeji.zhailuserver.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;

import java.util.List;

public class ReceiptAdapter extends BaseQuickAdapter<GetOrderBean.OrdersListBean, BaseViewHolder> {
    public ReceiptAdapter(int layoutResId, @Nullable List<GetOrderBean.OrdersListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderBean.OrdersListBean item) {
        helper.addOnClickListener(R.id.receipt_bt);
        if (item.getCate_id().equals("11")) {
              //配送时间
            helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getDelivery_time());
            //取件地址
            helper.setText(R.id.adapter_order_address, "收货地址：" + item.getEnd_address() + item.getEnd_detail());
            helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
            helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
        } else if (item.getCate_id().equals("12")) {
            //显示 adapter_item
            LinearLayout adapterItem=helper.getView(R.id.adapter_item);
            adapterItem.setVisibility(View.VISIBLE);
            //物品
            helper.setText(R.id.adapter_order_sex, "需要购买：" + item.getGoods());
            //揽件时间
            helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getDelivery_time());
            //取件地址
            if (TextUtils.isEmpty(item.getBuy_address())) {
                helper.setText(R.id.adapter_order_address, "购买地址：就近购买");
            } else {
                helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBuy_address());
            }
             helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
            helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
            helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
        } else if (item.getCate_id().equals("13")) {
            //显示 adapter_item
            LinearLayout adapterItem=helper.getView(R.id.adapter_item);
            adapterItem.setVisibility(View.VISIBLE);
            //物品
            helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getType_name());
            //揽件时间
            helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getDelivery_time());
            //取件地址
            helper.setText(R.id.adapter_order_address, "取件地址：" + item.getStart_address() + item.getStart_detail());
            helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
            helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
            helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
        } else if (item.getCate_id().equals("14")) {
            //显示 adapter_item
            LinearLayout adapterItem=helper.getView(R.id.adapter_item);
            adapterItem.setVisibility(View.VISIBLE);
            //物品
            helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getType_name());
            //揽件时间
            helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getDelivery_time());
            //取件地址
            helper.setText(R.id.adapter_order_address, "取件地址：" + item.getEnd_address() + item.getEnd_detail());
            helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
            helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
            helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
        } else if (item.getCate_id().equals("15")){
            //显示 adapter_item
            LinearLayout adapterItem=helper.getView(R.id.adapter_item);
            adapterItem.setVisibility(View.VISIBLE);
            //所需帮助
            helper.setText(R.id.adapter_order_sex, "所需帮助：" + item.getDemand());
            //揽件时间
            helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getDelivery_time());
            //取件地址
            helper.setText(R.id.adapter_order_address, "取件地址：" + item.getEnd_address() + item.getEnd_detail());
            helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
            helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
            helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
        }
        //类型
        helper.setText(R.id.adapter_order_type, item.getCate_name());
        //发布时间
        helper.setText(R.id.adapter_allorder_time, item.getCreate_time());
        //服务价格
        helper.setText(R.id.adapter_order_price, item.getTask_price());
    }

}
