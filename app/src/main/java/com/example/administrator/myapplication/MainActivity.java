package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tx_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        initView();
        setCompont();//设置组件效果

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
    }
}
