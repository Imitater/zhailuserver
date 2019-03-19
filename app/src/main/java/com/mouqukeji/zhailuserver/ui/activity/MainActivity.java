package com.mouqukeji.zhailuserver.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import com.bumptech.glide.Glide;
import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.base.BaseActivity;
import com.mouqukeji.zhailuserver.bean.CheckVersionBean;
import com.mouqukeji.zhailuserver.bean.InfoBean;
import com.mouqukeji.zhailuserver.bean.LocationUpBean;
import com.mouqukeji.zhailuserver.contract.activity.MainContract;
import com.mouqukeji.zhailuserver.model.activity.MainModel;
import com.mouqukeji.zhailuserver.presenter.activity.MainPresenter;
import com.mouqukeji.zhailuserver.ui.adapter.LeftAdapter;
import com.mouqukeji.zhailuserver.ui.adapter.MainPagerAdapter;
import com.mouqukeji.zhailuserver.ui.fragment.OrderFragment;
import com.mouqukeji.zhailuserver.ui.fragment.ReceiptFragment;
import com.mouqukeji.zhailuserver.update.ICheckAgent;
import com.mouqukeji.zhailuserver.update.IUpdateChecker;
import com.mouqukeji.zhailuserver.update.IUpdateParser;
import com.mouqukeji.zhailuserver.update.UpdateInfo;
import com.mouqukeji.zhailuserver.update.UpdateManager;
import com.mouqukeji.zhailuserver.utils.EventCode;
import com.mouqukeji.zhailuserver.utils.EventMessage;
import com.mouqukeji.zhailuserver.utils.GetSPData;
 import com.mouqukeji.zhailuserver.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.main_receipt_bt)
    RadioButton mainReceiptBt;
    @BindView(R.id.main_order_bt)
    RadioButton mainOrderBt;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.main_framelayout)
    FrameLayout mainFramelayout;
    @BindView(R.id.left_head)
    CircleImageView leftHead;
    @BindView(R.id.left_name)
    TextView leftName;
    @BindView(R.id.left_number)
    TextView leftNumber;
    @BindView(R.id.left_list)
    ListView leftList;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.main_user_left)
    Button mainUserLeft;
    private ActionBarDrawerToggle drawerbar;
    private List page;
    private String userId;
    private String spUserID;
    private boolean flag = true;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            //刷新msg的内容
            getLocation();
            handler.postDelayed(this, 1000 * 120);
        }
    };
    private String versionName;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getInfo(spUserID, mMultipleStateView);
        //版本检测
        versionName = PhoneUtils.getVersionName(this);
        mMvpPresenter.checkVersion(this, "android", versionName, "2", mMultipleStateView);
        //获取定位信息
        getLocation();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        //侧拉菜单设置
        initDrawlayout();
        //侧拉菜单list设置
        setDrawList();
        //顶部导航默认设置
        initDefaul();
        //点击事件
        initListener();
        //viewpager页面设置
        initPage();
        //设置viewpager
        setViewPager();
        //设置pageLIstener
        mainViewpager.setOnPageChangeListener(this);


    }

    private void setViewPager() {
        //刷新tab 下的fragment  重新设置adapter
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), page);
        mainViewpager.setAdapter(mainPagerAdapter);
        mainViewpager.setCurrentItem(0);
        mainViewpager.setOffscreenPageLimit(1);//ViewPager设置预加载页面的个数方法
    }

    //加载viewpager 页面
    private void initPage() {
        page = new ArrayList<Fragment>();
        page.add(new ReceiptFragment());
        page.add(new OrderFragment());
    }

    private void setDrawList() {
        LeftAdapter leftAdapter = new LeftAdapter(this);
        leftList.setAdapter(leftAdapter);
        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //左侧list点击事件
                listClock(position);
                drawerlayout.closeDrawer(mainFramelayout);
            }
        });
    }

    private void listClock(int position) {
        switch (position) {
//            case 0:
//                Intent intent = new Intent(MainActivity.this, IdentityActivity.class);
//                startActivity(intent);
//                break;
            case 0:
                Intent intent1 = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(MainActivity.this, PageActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent3);
                break;
        }
    }

    private void initListener() {
        mainUserLeft.setOnClickListener(this);
        mainReceiptBt.setOnClickListener(this);
        mainOrderBt.setOnClickListener(this);
        leftHead.setOnClickListener(this);
    }

    private void initDefaul() {
        //默认选中接单 背景色 蓝色
        mainReceiptBt.setTextColor(getResources().getColor(R.color.blue));
        mainOrderBt.setTextColor(getResources().getColor(R.color.main_gray));
    }

    private void initDrawlayout() {
        drawerbar = new ActionBarDrawerToggle(this, drawerlayout, null, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerlayout.setDrawerListener(drawerbar);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_user_left:
                drawerlayout.openDrawer(mainFramelayout);
                break;
            case R.id.main_receipt_bt://接单点击
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.blue));
                mainOrderBt.setTextColor(getResources().getColor(R.color.main_gray));
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.main_order_bt://订单点击
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.main_gray));
                mainOrderBt.setTextColor(getResources().getColor(R.color.blue));
                mainViewpager.setCurrentItem(1);
                break;
            case R.id.left_head://头像点击
                Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        handler.postDelayed(runnable, 1000 * 120);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.blue));
                mainOrderBt.setTextColor(getResources().getColor(R.color.main_gray));
                break;
            case 1:
                //默认选中接单 背景色 蓝色
                mainReceiptBt.setTextColor(getResources().getColor(R.color.main_gray));
                mainOrderBt.setTextColor(getResources().getColor(R.color.blue));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    public void getLocation() {
        //初始化定位
        AMapLocationClient mlocationClient = new AMapLocationClient(getApplicationContext());
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

        /**
         * 定位回调监听器
         */
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {

                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        //获取纬度
                        double currentLat = amapLocation.getLatitude();
                        //获取经度
                        double currentLon = amapLocation.getLongitude();
                        //上传位置信息
                        mMvpPresenter.locationUp(spUserID, currentLat + "", currentLon + "", mMultipleStateView);
                        /*currentLatLng = new LatLng(currentLat, currentLon);*/   //latlng形式的
                        Log.i("currentLocation", "currentLat : " + currentLat + " currentLon : " + currentLon);
                        amapLocation.getAccuracy();//获取精度信息
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };


        //设置定位监听
        mlocationClient.setLocationListener(mLocationListener);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        //如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        // 启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void locationUp(LocationUpBean bean) {
        //位置信息上传成功
        if (flag) {
            flag = false;
            handler.postDelayed(runnable, 1000);
        } else {
            handler.postDelayed(runnable, 1000 * 120);
        }
    }

    @Override
    public void getInfo(InfoBean bean) {
        Glide.with(this).load(bean.getUserInfo().getAvatar()).into(leftHead);
        leftName.setText(bean.getUserInfo().getNickname());
        leftNumber.setText(bean.getUserInfo().getUsername());
    }

    @Override
    public void checkVersion(CheckVersionBean backBean) {
        if (backBean.getVersionInfo().getIs_compel().equals("0")) {
            flag = false;
        } else {
            flag = true;
        }
        if (!backBean.getVersionInfo().getVersion_code().equals(versionName)) {
            check(false, true, false,
                    false, 998, backBean.getVersionInfo().getApk_md5(), backBean.getVersionInfo().getApk_size(), backBean.getVersionInfo().getApk_url(),
                    backBean.getVersionInfo().getVersion_code(), backBean.getVersionInfo().getUpdate_info(), flag);
        }
    }

    void check(boolean isManual, final boolean hasUpdate, final boolean isSilent,
               final boolean isIgnorable, final int notifyId, final String apk_md5,
               final String size, final String url,
               final String version_Name, final String upInfo
            , final boolean isForce) {
        UpdateManager.create(this).setChecker(new IUpdateChecker() {
            @Override
            public void check(ICheckAgent agent, String url) {
                agent.setInfo("");
            }
        }).setWifiOnly(true).setUrl("https://api.hmdeer.com/deer/Login/checkVersion?platform=android&version_code=" + versionName+"&type=2").setManual(isManual)
                .setNotifyId(notifyId).setParser(new IUpdateParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = hasUpdate;
                info.updateContent = upInfo;
                info.versionCode = 1;
                info.versionName = version_Name;
                info.url = url;
                info.md5 = apk_md5;
                info.isForce = isForce;
                info.size = Long.parseLong(size);
                info.isIgnorable = isIgnorable;
                info.isSilent = isSilent;
                return info;
            }
        }).check();
    }

    @Override
    public void isNeedUp() {

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_B) {
                //刷新头像
                mMvpPresenter.getInfo(spUserID, mMultipleStateView);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }

}
