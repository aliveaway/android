package com.example.administrator.myapplication.broadcast;

import android.content.IntentFilter;
import android.os.Bundle;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

public class NetStateActivity extends BaseActivity {

    private MyBRReceiver myBRReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registBroadcast();
    }

    private void registBroadcast() {
        myBRReceiver = new MyBRReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myBRReceiver, filter);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_net_state);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBRReceiver);
    }
}
