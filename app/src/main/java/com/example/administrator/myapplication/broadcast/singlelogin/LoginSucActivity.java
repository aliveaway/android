package com.example.administrator.myapplication.broadcast.singlelogin;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginSucActivity extends BaseActivity {

    private LogoutReceiver logoutReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;

    @BindView(R.id.logout_other)
    Button logoutOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logoutReceiver = new LogoutReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter("com.example.administrator.myapplication.broadcast.singlelogin.LogoutReceiver");
        localBroadcastManager.registerReceiver(logoutReceiver, intentFilter);

    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_login_suc);
    }

    @OnClick(R.id.logout_other)
    public void logout() {
        Intent i = new Intent("com.example.administrator.myapplication.broadcast.singlelogin.LogoutReceiver");
        localBroadcastManager.sendBroadcast(i);
    }
}
