package com.example.administrator.myapplication.drawable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MeziAdapter extends BaseAdapter {

    private Context mContext;
    private int[] mData;
    private LayoutInflater layoutInflater;

    public MeziAdapter(Context context, int[] mData) {
        this.mContext = context;
        this.mData = mData;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vieWHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.meinv_item, parent, false);
            vieWHolder = new ViewHolder();
            vieWHolder.imageView = (ImageView) convertView.findViewById(R.id.meinv_item);
            convertView.setTag(vieWHolder);
        } else {
            vieWHolder = (ViewHolder) convertView.getTag();
        }

        vieWHolder.imageView.setImageResource(mData[position]);

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }

}
