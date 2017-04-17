package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.component.QqConmentActivity;
import com.example.administrator.myapplication.component.TextViewLinkActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private Intent mIntent;

    private TextView tx_click;
    private Button go_picLink;
    @BindView(R.id.qq)
    Button qq_conment;

    @BindView(R.id.nav)
    Button navBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化
        initView();
        setCompont();//设置组件效果

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
}
