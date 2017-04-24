package com.example.administrator.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 网络发生改变时对应的广播接收器
 */
public class MyBRReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "网络状态发生改变~", Toast.LENGTH_SHORT).show();
    }
}
