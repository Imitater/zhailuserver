package com.mouqukeji.zhailuserver.utils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;

public class MultipleItem implements MultiItemEntity {
    public static final int Type1 = 3;
    public static final int Type2 = 8;
    public static final int Type3 = 9;
    public static final int Type4 = 4;
    public static final int Type5 = 5;

    private int itemType = 3;
    private PutOrderBean.OrdersListBean bean;

    public PutOrderBean.OrdersListBean getBean() {
        return bean;
    }

    public void setBean(PutOrderBean.OrdersListBean bean) {
        this.bean = bean;
    }

    public MultipleItem(int itemType, PutOrderBean.OrdersListBean bean) {
        this.itemType = itemType;
        this.bean=bean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}


