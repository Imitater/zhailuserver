package com.mouqukeji.zhailuserver.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;

import java.io.File;
import java.util.List;

public class OpenLocalMapUtil {
    /**
     * 跳转高德地图
     */
    public static void goToGaodeMap(Context context, LatLng latLng, String address) {
        if (!isInstalled("com.autonavi.minimap",context)) {
            Toast.makeText(context, "请先安装高德地图客户端",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("amapuri://route/plan/?sid=BGVIS1").append("宅鹿");
        stringBuffer.append("&dlat=").append(latLng.latitude)
                .append("&dlon=").append(latLng.longitude).append("&dname=" + address)
                .append("&dev=").append(0)
                .append("&style=").append(2) .append("&t=").append("2");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }
    /**
     * 检测程序是否安装
     *
     * @param packageName
     * @param context
     * @return
     */
    private static boolean isInstalled(String packageName, Context context) {
        PackageManager manager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

}
