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
import com.example.administrator.myapplication.component.DateTimeDemoActivity;
import com.example.administrator.myapplication.component.ProgressBarActivity;
import com.example.administrator.myapplication.component.QqConmentActivity;
import com.example.administrator.myapplication.component.ShowImageViewActivity;
import com.example.administrator.myapplication.component.TextViewLinkActivity;
import com.example.administrator.myapplication.component.listview.ListActivity;
import com.example.administrator.myapplication.dialog.AlertDialogActivity;
import com.example.administrator.myapplication.spinner.SpinnerActivity;
import com.example.administrator.myapplication.view.EditTextWithDel;

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
            , R.id.go_alert_dialog})
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
            default:
                break;
        }
    }
}
