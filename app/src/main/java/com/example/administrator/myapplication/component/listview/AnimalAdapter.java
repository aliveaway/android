package com.example.administrator.myapplication.component.listview;

import android.content.Context;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.CommonAdapter;
import com.example.administrator.myapplication.base.ViewHolder;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class AnimalAdapter extends CommonAdapter<Animal> {

    private LinkedList<Animal> mData;
    private Context mContext;

    public AnimalAdapter(LinkedList<Animal> mData, Context mContext) {
        super(mContext, mData, R.layout.item_list_animal);
    }

    /**
     * ViewHolder与数据绑定
     *
     * @param viewHolder
     * @param obj
     */
    @Override
    public void bindView(ViewHolder viewHolder, Animal obj) {
        viewHolder.setImageResource(R.id.img_icon, obj.getaIcon())
                .setText(R.id.txt_aName, obj.getaName())
                .setText(R.id.txt_aSpeak, obj.getaSpeak());
    }
}
