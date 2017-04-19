package com.example.administrator.myapplication.component;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;

/**
 * ProgressBar属性研究
 */
public class ProgressBarActivity extends BaseActivity {

    @BindView(R.id.progress_bar)
    ImageView img_pgbar;
    private AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ad = (AnimationDrawable) img_pgbar.getDrawable();
        img_pgbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                ad.start();
            }
        }, 100);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_progress_bar);
    }
}
