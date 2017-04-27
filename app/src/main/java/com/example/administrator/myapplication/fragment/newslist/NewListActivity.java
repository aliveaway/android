package com.example.administrator.myapplication.fragment.newslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewListActivity extends BaseActivity {

    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.fl_content)
    FrameLayout fl_content;

    private Context context;
    private FragmentManager fManager;
    private List<Data> datas;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fManager = getSupportFragmentManager();

        datas = new ArrayList<Data>();
        for (int i = 1; i <= 20; i++) {
            Data data = new Data("新闻标题" + i, i + "~新闻内容~~~~~~~~");
            datas.add(data);
        }

        NewListFragment newListFragment = new NewListFragment(fManager, datas);
        FragmentTransaction ft = fManager.beginTransaction();
        ft.replace(R.id.fl_content, newListFragment);
        ft.commit();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_new_list);
    }

    /**
     * 点击回退键的处理，判断 Fragment栈是否有Fragment
     * 有，popBackStack出栈
     * 没，双击退出，否则Toast提示
     */
    @Override
    public void onBackPressed() {
        if (fManager.getBackStackEntryCount() == 0) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fManager.popBackStack();
            txt_title.setText("新闻列表");
        }
    }

    public TextView getTitleTextView() {
        return txt_title;
    }
}
