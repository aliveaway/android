package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 制作一个首页导航
 * LinearLayout+TextView实现方式
 */
public class FragmentsActivity extends BaseActivity {


    @BindView(R.id.txt_topbar)
    TextView txt_topbar;
    @BindView(R.id.txt_channel)
    TextView txt_channel;
    @BindView(R.id.txt_message)
    TextView txt_message;
    @BindView(R.id.txt_better)
    TextView txt_better;
    @BindView(R.id.txt_setting)
    TextView txt_setting;
    @BindView(R.id.ly_content)
    FrameLayout ly_content;

    //Fragment Object
    private MyFragment fg1, fg2, fg3, fg4;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fManager = getSupportFragmentManager();
        txt_channel.performClick();

    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_fragments);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
        if (fg4 != null) fragmentTransaction.hide(fg4);
    }

    @OnClick({R.id.txt_channel, R.id.txt_message, R.id.txt_better, R.id.txt_setting})
    public void onClick(View view) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (view.getId()) {
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if (fg1 == null) {
                    fg1 = new MyFragment("第一个Fragment");
                    fTransaction.add(R.id.ly_content, fg1);
                } else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if (fg2 == null) {
                    fg2 = new MyFragment("第二个Fragment");
                    fTransaction.add(R.id.ly_content, fg2);
                } else {
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);
                if (fg3 == null) {
                    fg3 = new MyFragment("第三个Fragment");
                    fTransaction.add(R.id.ly_content, fg3);
                } else {
                    fTransaction.show(fg3);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if (fg4 == null) {
                    fg4 = new MyFragment("第四个Fragment");
                    fTransaction.add(R.id.ly_content, fg4);
                } else {
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }

}
