package com.example.administrator.myapplication.component.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class AnimalAdapter extends BaseAdapter {

    private LinkedList<Animal> mData;
    private Context mContext;

    public AnimalAdapter(LinkedList<Animal> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_animal, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img_icon.setBackgroundResource(mData.get(position).getaIcon());
        viewHolder.txt_aName.setText(mData.get(position).getaName());
        viewHolder.txt_aSpeak.setText(mData.get(position).getaSpeak());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_icon)
        ImageView img_icon;
        @BindView(R.id.txt_aName)
        TextView txt_aName;
        @BindView(R.id.txt_aSpeak)
        TextView txt_aSpeak;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
