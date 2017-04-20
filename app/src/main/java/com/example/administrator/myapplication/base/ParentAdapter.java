package com.example.administrator.myapplication.base;

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
 * 封装父类适配器
 */

public class ParentAdapter<T> extends BaseAdapter {

    private LinkedList<T> mData;
    private Context mContext;

    public ParentAdapter(LinkedList<T> mData, Context mContext) {
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

    /**
     * 添加一个数据
     *
     * @param data
     */
    public void add(T data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 往待定位置添加一个数据
     *
     * @param data
     */
    public void add(int postion, T data) {
        if (mData == null) {
            mData = new LinkedList<T>();
        }
        mData.add(postion, data);
        notifyDataSetChanged();
    }

    /**
     * 删除一个数据
     *
     * @param data
     */
    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 指定位置删除数据
     *
     * @param position
     */
    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
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
