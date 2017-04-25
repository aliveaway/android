package com.example.administrator.myapplication.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ContentProvideActivity extends BaseActivity {

    private static final int READ_SMS = 1001;
    private static final int READ_CONTACTS = 1002;
    @BindView(R.id.read_email)
    Button readEmail;

    @BindView(R.id.contacts)
    Button readContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_content_provide);
    }

    @OnClick({R.id.read_email, R.id.contacts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.read_email:
                //动态检查权限
                checkReadSms();
//                getMsg();
                break;
            case R.id.contacts:
                //动态检查权限
                checkReadContacts();
//                getMsg();
                break;
            default:
                break;
        }
    }

    private void checkReadContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}
                    , READ_CONTACTS);
        } else {
            readContacts();
        }
    }

    private void readContacts() {
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                    .Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                    .Phone.NUMBER));
            System.out.println("姓名：" + cName + "--电话：" + cNum);

        }
        cursor.close();
    }

    /**
     * 检查读取短信权限
     */
    private void checkReadSms() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请读短信权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, READ_SMS);
        } else {
            //有权限直接读
            getMsg();
        }

    }

    /**
     * 动态权限申请回调方法
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    /**
     * 根据权限申请结果，执行操作
     *
     * @param requestCode
     * @param grantResults
     */
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == READ_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMsg();
            } else {
                Toast.makeText(this, "申请读取邮件权限拒绝", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts();
            } else {
                Toast.makeText(this, "申请读取联系人权限拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 读取短信
     */
    private void getMsg() {
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"},
                null, null, null);
        while (cursor.moveToNext()) {
            String addr = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            System.out.println("地点" + addr + "--日期" + date + "--类型" + type + "--正文" + body);

        }
        cursor.close();
    }

}
