package com.example.administrator.myapplication.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20 0020.
 * 封装父类适配器
 * 通用
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    public CommonAdapter(Context context, List<T> mData, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
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

        //初始化ViewHolder
        ViewHolder viewHolder = ViewHolder.get(parent.getContext(), parent, convertView,
                mLayoutId, position);
        bindView(viewHolder, getItem(position));
        return viewHolder.getItemView();
    }

    public abstract void bindView(ViewHolder viewHolder, T obj);
}
