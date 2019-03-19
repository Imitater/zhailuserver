package com.mouqukeji.zhailuserver.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.ui.activity.BigImageActivity;
import com.mouqukeji.zhailuserver.ui.adapter.AccountListAdapter;
import com.mouqukeji.zhailuserver.ui.adapter.AllOrderAdapter;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;
import com.mouqukeji.zhailuserver.ui.widget.FullDialogView;


public class DialogUtils {
    //账户框
    public static CenterDialogView processDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable, final FrameLayout balanceFramelayout) {
        final CenterDialogView centerDialogView = new CenterDialogView(activity, view, isCancelable, isBackCancelable);
        centerDialogView.show();
        ImageView dialogAccountClose=centerDialogView.findViewById(R.id.dialog_account_close);

        dialogAccountClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDialogView.dismiss();//关闭dialog
            }
        });

        return centerDialogView;
     }
    public static void callDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable, final String end_telephone) {
        final CenterDialogView dialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        TextView dialogCall=dialogView.findViewById(R.id.dialog_call);
        dialogCall.setOnClickListener(new View.OnClickListener() {//拨打
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + end_telephone);
                intent.setData(data);
                context.startActivity(intent);
                dialogView.dismiss();
            }
        });
        TextView dialogDismiss=dialogView.findViewById(R.id.dialog_dismiss);
        dialogDismiss.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });
    }
    public static FullDialogView loadingDialog(final Context context, View view, boolean isCancelable, boolean isBackCancelable) {
        final FullDialogView dialogView = new FullDialogView(context, view, isCancelable, isBackCancelable);
        dialogView.show();
        return dialogView;
    }

    //凭证框
    public static CenterDialogView checkDialog(final Activity activity, final Context context, View view, final boolean isCancelable, boolean isBackCancelable, final String picture) {
        final CenterDialogView centerDialogView = new CenterDialogView(context, view, isCancelable, isBackCancelable);
        centerDialogView.show();

        Button dialogCheckClose = centerDialogView.findViewById(R.id.dialog_check_close);
        final TextView dialogCheckImage = centerDialogView.findViewById(R.id.dialog_check_image);
        TextView dialogCheckMoney = centerDialogView.findViewById(R.id.dialog_check_money);

        //设置关闭按键监听
        dialogCheckClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭进程框
                centerDialogView.dismiss();
            }
        });

        //点击查看大图
        dialogCheckImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BigImageActivity.class);
                intent.putExtra("pic", picture);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, dialogCheckImage, "image").toBundle();
                context.startActivity(intent, bundle);
            }
        });
        return centerDialogView;
    }

}
