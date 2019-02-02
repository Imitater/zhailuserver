package com.mouqukeji.zhailuserver.ui.adapter;

import android.support.annotation.Nullable;

import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.bean.LocationBean;

import java.util.ArrayList;
import java.util.List;

public class SelectLocationRecyclerviewAdapter extends BaseQuickAdapter<LocationBean, BaseViewHolder> {
    public SelectLocationRecyclerviewAdapter(int layoutResId, @Nullable ArrayList data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocationBean item) {
        helper.setText(R.id.adapter_select_address_tv,getData().get(helper.getLayoutPosition()).getTitle());
        helper.setText(R.id.adapter_select_address_contact_tv,getData().get(helper.getLayoutPosition()).getContent());
        helper.addOnClickListener(R.id.adapter_select_address_item);
    }

}
