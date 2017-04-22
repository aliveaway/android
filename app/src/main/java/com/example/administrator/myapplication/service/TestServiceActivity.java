package com.example.administrator.myapplication.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestServiceActivity extends BaseActivity {

    private static final String TAG = "TestServiceActivity";

    @BindView(R.id.startService)
    Button start;
    @BindView(R.id.stopService)
    Button stop;

    @BindView(R.id.lock_service)
    Button lock;
    @BindView(R.id.unlock)
    Button unlock;
    @BindView(R.id.status_service)
    Button status;

    final Intent mIntent = new Intent();
    final Intent mIntent2 = new Intent();

    //保持所启动的service的IBinder对象
    TestService2.MyBinder binder;
    //定义一个ServiceConnection对象
    private ServiceConnection connection = new ServiceConnection() {
        //Activity与Service连接成功回调的方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            System.out.println("------Service Connected-------");
            Log.i(TAG, "------Service Connected-------");
            binder = (TestService2.MyBinder) service;
        }

        //Activity与Service断开连接回调的方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
//            System.out.println("------Service DisConnected-------");
            Log.i(TAG, "------Service DisConnected-------");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent.setPackage(this.getPackageName());
        mIntent.setAction("com.example.administrator.myapplication.service.TestService");

        mIntent2.setPackage(this.getPackageName());
        mIntent2.setAction("com.example.administrator.myapplication.service.TestService2");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_test_service);

    }

    @OnClick({R.id.startService, R.id.stopService, R.id.lock_service, R.id.unlock,
            R.id.status_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startService:
                startService(mIntent);
                break;
            case R.id.stopService:
                stopService(mIntent);
                break;
            case R.id.lock_service:
                //绑定Service
                bindService(mIntent2, connection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unlock:
                //解除绑定
                unbindService(connection);
                break;
            case R.id.status_service:
                Toast.makeText(getApplicationContext(), "Service的count的值为:"
                        + binder.getCount(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
