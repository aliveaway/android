package com.example.administrator.myapplication.drawable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * 擦掉美女衣服
 * 相册展示页
 */
public class EraserTakeOffHomeActivity extends BaseActivity {

    @BindView(R.id.select_gril)
    ImageView select_girl;
    @BindView(R.id.take_of)
    Button take_of;
    @BindView(R.id.gal_choose)
    Gallery gal_choose;

    private int index;

    private MeziAdapter meziAdapter;
    private int[] imgIds = new int[]
            {
                    R.mipmap.pre1, R.mipmap.pre2, R.mipmap.pre3,
                    R.mipmap.pre4, R.mipmap.pre5, R.mipmap.pre6,
                    R.mipmap.pre7, R.mipmap.pre8, R.mipmap.pre9,
                    R.mipmap.pre10, R.mipmap.pre11, R.mipmap.pre12,
                    R.mipmap.pre13, R.mipmap.pre14, R.mipmap.pre15,
                    R.mipmap.pre16, R.mipmap.pre17, R.mipmap.pre18,
                    R.mipmap.pre19, R.mipmap.pre20, R.mipmap.pre21
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        meziAdapter = new MeziAdapter(this, imgIds);
        gal_choose.setAdapter(meziAdapter);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_eraser_take_off_home);
    }

    @OnItemSelected(R.id.gal_choose)
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        select_girl.setImageResource(imgIds[position]);
        index = position;
    }

    @OnClick(R.id.take_of)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_of:
                Intent it = new Intent(this, CaClothes.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("num", Integer.toString(index));
                it.putExtras(bundle);
                startActivity(it);
                break;
            default:
                break;
        }
    }

}
