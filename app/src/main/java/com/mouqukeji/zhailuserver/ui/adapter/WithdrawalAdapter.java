package com.mouqukeji.zhailuserver.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class WithdrawalAdapter extends BaseQuickAdapter <Integer, BaseViewHolder>{
    public WithdrawalAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {

    }

}
