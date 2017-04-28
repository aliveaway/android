package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.broadcast.NetStateActivity;
import com.example.administrator.myapplication.broadcast.singlelogin.LoginSingleActivity;
import com.example.administrator.myapplication.component.DateTimeDemoActivity;
import com.example.administrator.myapplication.component.ProgressBarActivity;
import com.example.administrator.myapplication.component.QqConmentActivity;
import com.example.administrator.myapplication.component.ShowImageViewActivity;
import com.example.administrator.myapplication.component.TextViewLinkActivity;
import com.example.administrator.myapplication.component.listview.ListActivity;
import com.example.administrator.myapplication.contentprovider.ContentProvideActivity;
import com.example.administrator.myapplication.dialog.AlertDialogActivity;
import com.example.administrator.myapplication.fragment.FragmentsActivity;
import com.example.administrator.myapplication.fragment.newslist.NewListActivity;
import com.example.administrator.myapplication.service.TestServiceActivity;
import com.example.administrator.myapplication.spinner.SpinnerActivity;
import com.example.administrator.myapplication.storage.StorageActivity;
import com.example.administrator.myapplication.view.EditTextWithDel;
import com.example.administrator.myapplication.webview.WebviewActivity;
import com.example.administrator.myapplication.webview.WebviewDialogActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private Intent mIntent;

    private TextView tx_click;
    private Button go_picLink;
    @BindView(R.id.qq)
    Button qq_conment;

    @BindView(R.id.nav)
    Button navBtn;

    @BindView(R.id.edit_del)
    EditTextWithDel editTextWithDel;

    @BindView(R.id.imgView)
    Button imgView;

    @BindView(R.id.progressbar)
    Button progressbar;

    @BindView(R.id.go_list)
    Button goList;


    @BindView(R.id.go_spinner)
    Button goSpinner;

    @BindView(R.id.go_alert_dialog)
    Button goAlertDialog;

    @BindView(R.id.go_service)
    Button goService;

    @BindView(R.id.go_net_change)
    Button goBroadcast;

    @BindView(R.id.go_sin_login)
    Button goSingleLogin;

    @BindView(R.id.go_con_provider)
    Button goProvider;

    @BindView(R.id.go_home_demo1)
    Button goHome;

    @BindView(R.id.new_list)
    Button goNewsList;

    @BindView(R.id.go_storage)
    Button goStorage;
    @BindView(R.id.web_view)
    Button web_view;
    @BindView(R.id.web_dialog)
    Button web_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate调用");
        //初始化
        initView();
        setCompont();//设置组件效果

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy调用");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    private void setCompont() {
        String s = "<font color='blue'><b>百度一下，你就知道</b></font>";
        s += "<a href='https://www.baidu.com'>百度</a>";
        tx_click.setText(Html.fromHtml(s));
        tx_click.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     *
     */
    private void initView() {
        tx_click = (TextView) findViewById(R.id.tx_click);
        go_picLink = (Button) findViewById(R.id.button);
    }

    @OnClick(R.id.button)
    public void goTextView() {
        mIntent = new Intent(this, TextViewLinkActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.qq)
    public void goQqComment(View view) {
        mIntent = new Intent(this, QqConmentActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.nav)
    public void goNav() {
        mIntent = new Intent(this, Main2Activity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.imgView)
    public void showImgView() {
        mIntent = new Intent(this, ShowImageViewActivity.class);
        startActivity(mIntent);
    }

    @OnClick({R.id.progressbar, R.id.date_time, R.id.go_list, R.id.go_spinner
            , R.id.go_alert_dialog, R.id.go_service, R.id.go_net_change, R.id.go_sin_login,
            R.id.go_con_provider, R.id.go_home_demo1, R.id.new_list, R.id.go_storage
            , R.id.web_view, R.id.web_dialog})
    public void cusClick(View view) {
        switch (view.getId()) {
            case R.id.progressbar:
                mIntent = new Intent(this, ProgressBarActivity.class);
                startActivity(mIntent);
                break;
            case R.id.date_time:
                mIntent = new Intent(this, DateTimeDemoActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_list:
                mIntent = new Intent(this, ListActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_spinner:
                mIntent = new Intent(this, SpinnerActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_alert_dialog:
                mIntent = null;
                mIntent = new Intent(this, AlertDialogActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_service:
                mIntent = null;
                mIntent = new Intent(this, TestServiceActivity.class);
                startActivity(mIntent);
                break;

            case R.id.go_net_change:
                mIntent = null;
                mIntent = new Intent(this, NetStateActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_sin_login:
                mIntent = null;
                mIntent = new Intent(this, LoginSingleActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_con_provider:
                mIntent = null;
                mIntent = new Intent(this, ContentProvideActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_home_demo1:
                mIntent = null;
                mIntent = new Intent(this, FragmentsActivity.class);
                startActivity(mIntent);
                break;
            case R.id.new_list:
                mIntent = null;
                mIntent = new Intent(this, NewListActivity.class);
                startActivity(mIntent);
                break;
            case R.id.go_storage:
                //文件存贮
                mIntent = null;
                mIntent = new Intent(this, StorageActivity.class);
                startActivity(mIntent);
                break;
            case R.id.web_view:
                mIntent = null;
                mIntent = new Intent(this, WebviewActivity.class);
                startActivity(mIntent);
                break;
            case R.id.web_dialog:
                mIntent = null;
                mIntent = new Intent(this, WebviewDialogActivity.class);
                startActivity(mIntent);
                break;
            default:
                break;
        }
    }
}
