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

    @BindView(R.id.intent_service)
    Button intent_service;

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

        //必须设置，否则报错
        mIntent2.setPackage(this.getPackageName());
        mIntent2.setAction("com.example.administrator.myapplication.service.TestService2");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_test_service);

    }

    @OnClick({R.id.startService, R.id.stopService, R.id.lock_service, R.id.unlock,
            R.id.status_service, R.id.intent_service})
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
            case R.id.intent_service:
                builderIntentService();
                break;
            default:
                break;
        }
    }

    private void builderIntentService() {
        Intent it1 = new Intent("com.example.administrator.myapplication.service.TestService3");
        it1.setPackage(this.getPackageName());
        Bundle b1 = new Bundle();
        b1.putString("param", "s1");
        it1.putExtras(b1);

        Intent it2 = new Intent("com.example.administrator.myapplication.service.TestService3");
        it2.setPackage(this.getPackageName());
        Bundle b2 = new Bundle();
        b2.putString("param", "s2");
        it2.putExtras(b2);

        Intent it3 = new Intent("com.example.administrator.myapplication.service.TestService3");
        it3.setPackage(this.getPackageName());
        Bundle b3 = new Bundle();
        b3.putString("param", "s3");
        it3.putExtras(b3);

        //接着启动多次IntentService,每次启动,都会新建一个工作线程
        //但始终只有一个IntentService实例
        startService(it1);
        startService(it2);
        startService(it3);
    }

}
