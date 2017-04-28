package com.example.administrator.myapplication.webview;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;

public class WebviewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebSettings webSettings = web_view.getSettings();
        //①设置WebView允许调用js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        //②将object对象暴露给Js,调用addjavascriptInterface
        web_view.addJavascriptInterface(new MyObject(WebviewActivity.this), "myObj");

        web_view.loadUrl("file:///android_asset/demo1.html");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_webview);
    }
}
