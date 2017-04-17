package com.example.administrator.myapplication.component;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;

public class QqConmentActivity extends BaseActivity {

    @BindView(R.id.tv_coment)
    TextView tvComent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTvComent();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_qq_conment);
    }

    /**
     * 构建qq好友评论人列表
     */
    private void setTvComent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("好友" + i + "，");
        }

        String likeUsers = sb.substring(0, sb.lastIndexOf("，"));
        tvComent.setMovementMethod(LinkMovementMethod.getInstance());
        tvComent.setText(addClickPart(likeUsers), TextView.BufferType.SPANNABLE);

    }

    /**
     * 定义一个点击每个部分文字的处理方法
     *
     * @param likeUsers
     * @return
     */
    private SpannableStringBuilder addClickPart(String str) {
//        笑脸图标
        ImageSpan imgSpan = new ImageSpan(QqConmentActivity.this, R.mipmap.ic_launcher_round);
        SpannableString spanStr = new SpannableString("p.");
        spanStr.setSpan(imgSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //创建一个SpannableStringBuilder对象，连接多个字符串
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);

        String[] likeUsers = str.split("，");

        if (likeUsers.length > 0) {
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                final int start = str.indexOf(name) + spanStr.length();
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(QqConmentActivity.this, name, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                }, start, start, 0);
            }
        }

        return ssb.append("等" + likeUsers.length + "个人觉得很赞");
    }

}
