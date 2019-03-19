package com.mouqukeji.zhailuserver.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.bean.GetOrderBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.TakeOrderInfoBean;
import com.mouqukeji.zhailuserver.ui.activity.HelpBuyInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpDeliverInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpSendInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpTakeInfoActivity;
import com.mouqukeji.zhailuserver.ui.activity.HelpUniversalInfoActivity;
import com.mouqukeji.zhailuserver.utils.MultipleItem;

import java.util.List;

public class AllOrderAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    public AllOrderAdapter(Context mContext, List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.Type1, R.layout.adapter_un_complete);
        addItemType(MultipleItem.Type2, R.layout.adapter_un_complete);
        addItemType(MultipleItem.Type3, R.layout.adapter_un_complete);
        addItemType(MultipleItem.Type4, R.layout.adapter_complete);
        addItemType(MultipleItem.Type5, R.layout.adapter_complete);
    }


    @Override
    protected void convert(BaseViewHolder helper, final MultipleItem item) {
        switch (helper.getItemViewType()) {
            case 3:
                if (item.getBean().getCate_id().equals("11")) {
                    //配送时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    //判断订单状态 设置bt text
                    if (item.getBean().getProgress().equals("3")) {
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpTakeInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("12")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "需要购买：" + item.getBean().getGoods());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    if (TextUtils.isEmpty(item.getBean().getBuy_address())) {
                        helper.setText(R.id.adapter_order_address, "购买地址：就近购买");
                    } else {
                        helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getBuy_address());
                    }
                     if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认购买");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }

                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpBuyInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("13")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" +item.getBean().getStart_address() + item.getBean().getStart_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpSendInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("14")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认寄出");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpDeliverInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("15")){
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "所需帮忙：" + item.getBean().getDemand());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认出发");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpUniversalInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                 }
                //类型
                helper.setText(R.id.adapter_order_type, item.getBean().getCate_name());
                //发布时间
                helper.setText(R.id.adapter_allorder_time, item.getBean().getCreate_time());
                //服务价格
                helper.setText(R.id.adapter_order_price, item.getBean().getTask_price());
                helper.addOnClickListener(R.id.adapter_order_bt);
                break;
            case 8:
                if (item.getBean().getCate_id().equals("11")) {
                      //配送时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "购买地址：" + item.getBean().getEnd_address()+item.getBean().getEnd_detail());
                    //判断订单状态 设置bt text
                    if (item.getBean().getProgress().equals("3")) {
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }

                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpTakeInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("12")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "需要购买：" + item.getBean().getGoods());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    if (TextUtils.isEmpty(item.getBean().getBuy_address())) {
                        helper.setText(R.id.adapter_order_address, "购买地址：就近购买");
                    } else {
                        helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getBuy_address());
                    }

                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认购买");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }

                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpBuyInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("13")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" +item.getBean().getStart_address() + item.getBean().getStart_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpSendInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("14")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认寄出");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpDeliverInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("15")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "所需帮忙：" + item.getBean().getDemand());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认出发");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpUniversalInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                 }
                //类型
                helper.setText(R.id.adapter_order_type, item.getBean().getCate_name());
                //发布时间
                helper.setText(R.id.adapter_allorder_time, item.getBean().getCreate_time());
                //服务价格
                helper.setText(R.id.adapter_order_price, item.getBean().getTask_price());
                helper.addOnClickListener(R.id.adapter_order_bt);
                break;
            case 9:
                if (item.getBean().getCate_id().equals("11")) {
                      //配送时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    //判断订单状态 设置bt text
                    if (item.getBean().getProgress().equals("3")) {
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }

                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpTakeInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("12")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "需要购买：" + item.getBean().getGoods());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    if (TextUtils.isEmpty(item.getBean().getBuy_address())) {
                        helper.setText(R.id.adapter_order_address, "购买地址：就近购买");
                    } else {
                        helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getBuy_address());
                    }

                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认购买");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }

                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpBuyInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("13")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" +item.getBean().getStart_address() + item.getBean().getStart_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认送达");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpSendInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("14")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认取件");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认寄出");
                    } else if (item.getBean().getProgress().equals("9")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpDeliverInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("15")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "所需帮忙：" + item.getBean().getDemand());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认出发");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpUniversalInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                 }
                //类型
                helper.setText(R.id.adapter_order_type, item.getBean().getCate_name());
                //发布时间
                helper.setText(R.id.adapter_allorder_time, item.getBean().getCreate_time());
                //服务价格
                helper.setText(R.id.adapter_order_price, item.getBean().getTask_price());
                helper.addOnClickListener(R.id.adapter_order_bt);
                break;
            case 4:
                if (item.getBean().getCate_id().equals("11")) {
                      //配送时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpTakeInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("12")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "需要购买：" + item.getBean().getGoods());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    if (TextUtils.isEmpty(item.getBean().getBuy_address())) {
                        helper.setText(R.id.adapter_order_address, "购买地址：就近购买");
                    } else {
                        helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getBuy_address());
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpBuyInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("13")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" +item.getBean().getStart_address() + item.getBean().getStart_detail());
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpSendInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("14")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpDeliverInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("15")) {
                     //物品
                    helper.setText(R.id.adapter_order_sex, "所需服务：" + item.getBean().getDemand());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认出发");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpUniversalInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                 }
                //类型
                helper.setText(R.id.adapter_order_type, item.getBean().getCate_name());
                //发布时间
                helper.setText(R.id.adapter_allorder_time, item.getBean().getCreate_time());
                //服务价格
                helper.setText(R.id.adapter_order_price, item.getBean().getTask_price());
                break;
            case 5:
                if (item.getBean().getCate_id().equals("11")) {
                    //配送时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpTakeInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("12")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "需要购买：" + item.getBean().getGoods());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    if (TextUtils.isEmpty(item.getBean().getBuy_address())) {
                        helper.setText(R.id.adapter_order_address, "购买地址：就近购买");
                    } else {
                        helper.setText(R.id.adapter_order_address, "收货地址：" + item.getBean().getBuy_address());
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpBuyInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_goumai);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("13")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" +item.getBean().getStart_address() + item.getBean().getStart_detail());
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpSendInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("14")) {
                    helper.setVisible(R.id.adapter_item1,true);
                    //物品
                    helper.setText(R.id.adapter_order_sex, "物品类型：" + item.getBean().getType_name());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpDeliverInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                } else if (item.getBean().getCate_id().equals("15")) {
                    //物品
                    helper.setText(R.id.adapter_order_sex, "所需服务：" + item.getBean().getDemand());
                    //揽件时间
                    helper.setText(R.id.adapter_allorder_gettime, "揽件时间：" + item.getBean().getDelivery_time());
                    //取件地址
                    helper.setText(R.id.adapter_order_address, "取件地址：" + item.getBean().getEnd_address() + item.getBean().getEnd_detail());
                    if (item.getBean().getProgress().equals("3")) {
                        //待取货
                        helper.setText(R.id.adapter_order_bt, "确认出发");
                    } else if (item.getBean().getProgress().equals("8")) {
                        helper.setText(R.id.adapter_order_bt, "确认完成");
                    }
                    //进入详情页面
                    helper.setOnClickListener(R.id.adapter_item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HelpUniversalInfoActivity.class);
                            intent.putExtra("order_id", item.getBean().getOrder_id());
                            intent.putExtra("cate_id", item.getBean().getCate_id());
                            intent.putExtra("progress", item.getBean().getProgress());
                            mContext.startActivity(intent);
                        }
                    });
                    helper.setBackgroundRes(R.id.adapter_iv1,R.mipmap.mipmap_category);
                    helper.setBackgroundRes(R.id.adapter_iv2,R.mipmap.mipmap_time);
                    helper.setBackgroundRes(R.id.adapter_iv3,R.mipmap.mipmap_dizhi);
                }
                //类型
                helper.setText(R.id.adapter_order_type, item.getBean().getCate_name());
                //发布时间
                helper.setText(R.id.adapter_allorder_time, item.getBean().getCreate_time());
                //服务价格
                helper.setText(R.id.adapter_order_price, item.getBean().getTask_price());
                break;
        }
    }
}
