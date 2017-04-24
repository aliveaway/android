package com.example.administrator.myapplication.broadcast.singlelogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 模拟单点登陆
 */
public class LoginSingleActivity extends BaseActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!preferences.getString("user", "").equals("")) {
            account.setText(preferences.getString("user", ""));
            pwd.setText(preferences.getString("pwd", ""));
        }
    }

    @OnClick(R.id.login)
    public void login() {
        String user = account.getText().toString();
        String pwdTx = pwd.getText().toString();
        if (user.equals("123") && pwdTx.equals("123")) {
            //用户名和密码正确,保存
            editor = preferences.edit();
            editor.putString("user", user);
            editor.putString("pwd", pwdTx);
            editor.commit();

            Intent intent = new Intent(this, LoginSucActivity.class);
            startActivity(intent);
            Toast.makeText(this, "居然猜对了", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "这么简单都输出，脑子呢？", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_login_single);
    }
}
