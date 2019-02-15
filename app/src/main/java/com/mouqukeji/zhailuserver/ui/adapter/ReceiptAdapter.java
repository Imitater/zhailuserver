package com.mouqukeji.zhailuserver.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.zhailuserver.R;

import java.util.List;

public class ReceiptAdapter extends BaseQuickAdapter <Integer, BaseViewHolder>{
    public ReceiptAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer integer) {
        helper.addOnClickListener(R.id.receipt_bt);
    }

}
