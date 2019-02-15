package com.mouqukeji.zhailuserver.utils;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mouqukeji.zhailuserver.R;
import com.mouqukeji.zhailuserver.ui.adapter.AccountListAdapter;
import com.mouqukeji.zhailuserver.ui.adapter.AllOrderAdapter;
import com.mouqukeji.zhailuserver.ui.widget.CenterDialogView;


public class DialogUtils {
    //账户框
    public static CenterDialogView processDialog(final Activity activity, View view, final boolean isCancelable, boolean isBackCancelable, final FrameLayout balanceFramelayout) {
        final CenterDialogView centerDialogView = new CenterDialogView(activity, view, isCancelable, isBackCancelable);
        centerDialogView.show();
        ImageView dialogAccountClose=centerDialogView.findViewById(R.id.dialog_account_close);
        RecyclerView dialogAccountRecyclerview=centerDialogView.findViewById(R.id.dialog_account_recyclerview);

        dialogAccountClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerDialogView.dismiss();//关闭dialog
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        dialogAccountRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //AccountListAdapter accountListAdapter = new AccountListAdapter(R.layout.adapter_account_list, list);
        //dialogAccountRecyclerview.setAdapter(accountListAdapter);

        return centerDialogView;
     }

}
