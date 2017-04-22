package com.example.administrator.myapplication.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AlertDialogActivity extends BaseActivity {

    private static final String TAG = "AlertDialogActivity";

    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    @BindView(R.id.common_dia)
    Button commonDiglog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate调用");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_alert_dialog);
    }

    @OnClick({R.id.common_dia})
    public void cusClick(View view) {
        switch (view.getId()) {
            case R.id.common_dia:
                dialog = null;
                builder = new AlertDialog.Builder(AlertDialogActivity.this);
                dialog = builder.setTitle("你好")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("普通对话框")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogActivity.this, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                dialog.show();
                break;

            default:
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //AlertDialog显示的时候此方法不会调用
        Log.i(TAG, "onPause调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy调用");
    }
}
