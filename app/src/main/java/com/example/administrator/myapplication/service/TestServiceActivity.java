package com.example.administrator.myapplication.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestServiceActivity extends BaseActivity {
    @BindView(R.id.startService)
    Button start;
    @BindView(R.id.stopService)
    Button stop;

    final Intent mIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent.setPackage(this.getPackageName());
        mIntent.setAction("com.example.administrator.myapplication.service.TestService");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_test_service);

    }

    @OnClick({R.id.startService, R.id.stopService})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startService:
                startService(mIntent);
                break;
            case R.id.stopService:
                stopService(mIntent);
                break;
        }
    }
}
