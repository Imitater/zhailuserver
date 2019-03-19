package com.mouqukeji.zhailuserver.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;

public class LeftAdapter extends BaseAdapter {
    private final Context context;
    String[] titlt={"我的资料","我的钱包","系统设置"};
    int[] img={R.mipmap.mipmap_zhiliao,R.mipmap.mipmap_zhanghu,R.mipmap.mipmap_setting};
    public LeftAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return titlt.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.adapter_left_layout, null);
        ImageView adapterLeftIv=convertView.findViewById(R.id.adapter_left_iv);
        TextView adapterLeftTv=convertView.findViewById(R.id.adapter_left_tv);
        adapterLeftTv.setText(titlt[position]);
        adapterLeftIv.setBackgroundResource(img[position]);
        return convertView;
    }
}
