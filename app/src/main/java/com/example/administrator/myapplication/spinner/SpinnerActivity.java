package com.example.administrator.myapplication.spinner;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.CommonAdapter;
import com.example.administrator.myapplication.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnItemSelected;

public class SpinnerActivity extends BaseActivity {

    @BindView(R.id.spin_one)
    Spinner spin_one;
    @BindView(R.id.spin_two)
    Spinner spin_two;
    private Context mContext;
    //判断是否为刚进去时触发onItemSelected的标志
    private boolean one_selected = false;
    private boolean two_selected = false;
    private ArrayList<Hero> mData = null;
    private BaseAdapter myAdadpter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SpinnerActivity.this;
        mData = new ArrayList<Hero>();
        bindViews();
    }

    private void bindViews() {
        mData.add(new Hero(R.mipmap.iv_lol_icon1, "迅捷斥候：提莫（Teemo）"));
        mData.add(new Hero(R.mipmap.iv_lol_icon2, "诺克萨斯之手：德莱厄斯（Darius）"));
        mData.add(new Hero(R.mipmap.iv_lol_icon3, "无极剑圣：易（Yi）"));
        mData.add(new Hero(R.mipmap.iv_lol_icon4, "德莱厄斯：德莱文（Draven）"));
        mData.add(new Hero(R.mipmap.iv_lol_icon5, "德邦总管：赵信（XinZhao）"));
        mData.add(new Hero(R.mipmap.iv_lol_icon6, "狂战士：奥拉夫（Olaf）"));
        myAdadpter = new CommonAdapter<Hero>(this, mData, R.layout.item_spin_hero) {
            @Override
            public void bindView(ViewHolder holder, Hero obj) {
                holder.setImageResource(R.id.img_icon, obj.gethIcon());
                holder.setText(R.id.txt_name, obj.gethName());
            }
        };
        spin_two.setAdapter(myAdadpter);

    }

    @OnItemSelected({R.id.spin_one, R.id.spin_two})
    public void onItemSeleced(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_one:
                if (one_selected) {
                    Toast.makeText(mContext, "您的分段是~：" + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                } else
                    one_selected = true;
                break;
            case R.id.spin_two:
                if (two_selected) {
                    TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
                    Toast.makeText(mContext, "您选择的英雄是~：" + txt_name.getText().toString(), Toast.LENGTH_SHORT).show();
                } else
                    two_selected = true;
                break;
        }
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_spinner);
    }
}
