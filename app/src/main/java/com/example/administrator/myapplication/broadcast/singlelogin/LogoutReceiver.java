package com.example.administrator.myapplication.broadcast.singlelogin;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.example.administrator.myapplication.utils.ActivityCollector;

public class LogoutReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("警告")
                .setMessage("你的账号在别处登陆,请重新登陆")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCollector.finishAll();
                        Intent i = new Intent(context, LoginSingleActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        dialog.show();

    }
}