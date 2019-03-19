package com.mouqukeji.zhailuserver.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseFragment;
import com.mouqukeji.zhailuserver.base.BaseLazyFragment;
import com.mouqukeji.zhailuserver.bean.ConfirmServiceBean;
import com.mouqukeji.zhailuserver.bean.DeleteBean;
import com.mouqukeji.zhailuserver.bean.DeleteTrackBean;
import com.mouqukeji.zhailuserver.bean.PutOrderBean;
import com.mouqukeji.zhailuserver.bean.SearchSericeBean;
import com.mouqukeji.zhailuserver.bean.SendOrderBean;
import com.mouqukeji.zhailuserver.bean.SericeBean;
import com.mouqukeji.zhailuserver.bean.TrackBean;
import com.mouqukeji.zhailuserver.contract.fragment.DistributionContract;
import com.mouqukeji.zhailuserver.model.fragment.DistributionModel;
import com.mouqukeji.zhailuserver.presenter.fragment.DistributionPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.DistributionAdapter;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;
import com.mouqukeji.zhailuserver.ui.widget.FullDialogView;
import com.mouqukeji.zhailuserver.utils.DateUtils;
import com.mouqukeji.zhailuserver.utils.DialogUtils;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
import com.mouqukeji.zhailuserver.utils.KeyUtils;
import com.mouqukeji.zhailuserver.utils.TokenHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mouqukeji.zhailuserver.utils.EventBusUtils.post;

public class DistributionFragment extends BaseLazyFragment<DistributionPresenter, DistributionModel> implements DistributionContract.View, View.OnClickListener {
    @BindView(R.id.distribution_recyclerview)
    RecyclerView distributionRecyclerview;
    @BindView(R.id.distribution_swiperefreshlayout)
    SwipeRefreshLayout distributionSwiperefreshlayout;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    private int page = 1;
    private DistributionAdapter distributionAdapter;
    private String spUserID;
    private boolean flag = true;
    private List<PutOrderBean.OrdersListBean> ordersList;
    private String order_id;
    private long sid;
    private int pages;
    private String url;
    List<LocalMedia> listpic = new ArrayList<>();
    private ImageView dialogIv;
    private FullDialogView fullDialogView;


    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.putOrder(spUserID, "8", page + "", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_distribution;
    }

    @Override
    protected void setUpView() {

    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        distributionRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        distributionAdapter = new DistributionAdapter(R.layout.adapter_allorder, ordersList);
        distributionRecyclerview.setAdapter(distributionAdapter);
        distributionAdapter.disableLoadMoreIfNotFullPage(distributionRecyclerview);

        distributionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (distributionAdapter.getData().get(i).getCate_id().equals("13")&&distributionAdapter.getData().get(i).getExpress_pay_type().equals("1")){
                   //显示支付弹框
                    ItemsSendPriceDialog(getActivity(), getLayoutInflater().inflate(R.layout.dialog_helpbuy, null), true, true, distributionAdapter.getData().get(i).getOrder_id());
                }else {
                    order_id = distributionAdapter.getData().get(i).getOrder_id();
                    mMvpPresenter.confirmService(distributionAdapter.getData().get(i).getOrder_id(), mMultipleStateView);
                }
            }
        });
    }
    //商品价格框
    public void ItemsSendPriceDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable, final String order_id) {
        final CenterDialogView centerDialogView = new CenterDialogView(activity, view, isCancelable, isBackCancelable);
        centerDialogView.show();
        LinearLayout dialogBuyInfoPic = centerDialogView.findViewById(R.id.dialog_buy_info_pic);
        final EditText dialogBuyInfoEt = centerDialogView.findViewById(R.id.dialog_buy_info_et);
        Button dialogBuyInfoBt = centerDialogView.findViewById(R.id.dialog_buy_info_bt);
        ImageView dialogBuyInfoClose = centerDialogView.findViewById(R.id.dialog_buy_info_close);
        dialogIv = centerDialogView.findViewById(R.id.dialog_iv);
        dialogBuyInfoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDialogView.dismiss();
            }
        });
        dialogBuyInfoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putcheck();
            }
        });
        dialogBuyInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = dialogBuyInfoEt.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(getContext(), "请上传凭证图片", Toast.LENGTH_SHORT).show();
                } else if (money.equals("-1")) {
                    Toast.makeText(getContext(), "请输入商品价格", Toast.LENGTH_SHORT).show();
                } else {
                    mMvpPresenter.sendOrder(order_id, spUserID, url, money, mMultipleStateView);
                }
                centerDialogView.dismiss();
            }
        });
    }


    private void putcheck() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .cropCompressQuality(50)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMedia(listpic)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    //上传图片到九牛
    public void uploadImgSignQiNiu(final String path) {
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        String key = "icon_" + num + DateUtils.getData();
        TokenHelper tokenHelper = TokenHelper.create("Nwz4XdKR-G777FoMf-DrjaySeCWvjiwv7gd4sIm1", "aZkyjMBELmPthFf-60rwJQKR0eXYazHydDG8uF4H");
        String token = tokenHelper.getToken("mouqukeji");
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(path, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res包含hash、key等信息，具体字段取决于上传策略的设置
                if (info.isOK()) {
                    url = "http://picture.mouqukeji.com/" + key;
                    Glide.with(getMContext()).load(url).into(dialogIv);
                    fullDialogView.dismiss();
                 } else {
                    Log.i("picture", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("picture", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    listpic = PictureSelector.obtainMultipleResult(data);
                    uploadImgSignQiNiu(listpic.get(0).getCompressPath());
                    fullDialogView = DialogUtils.loadingDialog(getMContext(), getLayoutInflater().inflate(R.layout.dialog_loading, null), false, false);
                    break;
            }
        }
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        if (distributionSwiperefreshlayout != null) {
            distributionSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            distributionSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    distributionRecyclerview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page = 1;
                            mMvpPresenter.putOrder(spUserID, "8", page + "", mMultipleStateView);
                            if (distributionAdapter != null) {
                                distributionAdapter.notifyDataSetChanged();
                                distributionAdapter.setUpFetching(false);
                                distributionAdapter.setUpFetchEnable(false);
                            }
                            if (distributionSwiperefreshlayout!=null)
                            distributionSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }


    private void setUpLoad() {
        //recyclerview 滑动动画设置
        distributionAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        distributionAdapter.isFirstOnly(false);//设置动画一直使用
        if (ordersList.size() >= 10) {
            distributionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, distributionRecyclerview);
        }
    }

    private void setLoadMore() {
        distributionRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > pages) {
                    //数据全部加载完毕
                    distributionAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.putOrderNext(spUserID, "8", page + "", mMultipleStateView);
                }
            }
        }, 1500);
    }

    @Override
    protected void setUpData() {
     }

    @Override
    protected void lazyLoad() {
        if (!mIsprepared || !mIsVisible || mHasLoadedOnce) {
            return;
        }
        mHasLoadedOnce = true;
        page=1;
        mMvpPresenter.putOrder(spUserID, "8", page + "", mMultipleStateView);
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void putOrder(PutOrderBean bean) {
        pages = bean.getPages();
        page++;
        if (llNoOrder != null && distributionRecyclerview != null) {
            llNoOrder.setVisibility(View.GONE);
            distributionRecyclerview.setVisibility(View.VISIBLE);
            ordersList = bean.getOrdersList();
            //设置recyclerview
            setRecyclerview();
        }
        //设置上拉加载
        setUpLoad();
        //设置下拉刷新
        initSwipeRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (distributionSwiperefreshlayout != null) {
            distributionSwiperefreshlayout.setRefreshing(false);
        }
    }


    @Override
    public void putOrderNext(PutOrderBean bean) {
        //成功获取更多数据
        distributionAdapter.addData(bean.getOrdersList());
        distributionAdapter.loadMoreComplete();
        page++;
    }

    @Override
    public void isEmpty() {
        //关闭首次进入时刷新
        if (distributionSwiperefreshlayout!=null) {
            if (distributionSwiperefreshlayout.isRefreshing()) {
                distributionSwiperefreshlayout.setRefreshing(false);
            }
        }
        if (llNoOrder != null && distributionRecyclerview != null) {
            llNoOrder.setVisibility(View.VISIBLE);
            distributionRecyclerview.setVisibility(View.GONE);
        }
        initSwipeRefresh();
    }

    @Override
    public void confirmService(ConfirmServiceBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
    }

    @Override
    public void sendOrder(SendOrderBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
    }
    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_Z) {
                page = 1;
                mMvpPresenter.putOrder(spUserID, "8", page + "", mMultipleStateView);
                distributionAdapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    protected boolean isRegisteredEventBus() {
        return  true;
    }
}
