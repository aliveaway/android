package com.example.administrator.myapplication.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/20 0020.
 * 通用的ViewHolder封装
 */

public class ViewHolder {

    private SparseArray<View> mViews;   //存贮ListView中item中的View
    private View convertView;           //存放convertView
    private Context context;            //Context上下文
    private int position;               //游标

    /**
     * 初始化ViewHolder
     *
     * @param context
     * @param parent
     * @param layoutRes
     */
    private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
        mViews = new SparseArray<>();
        this.context = context;
        View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        convertView.setTag(this);
        this.convertView = convertView;

    }

    /**
     * 获取ViewHolder
     *
     * @param context
     * @param parent
     * @param convertView
     * @param layoutRes
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, ViewGroup parent, View convertView,
                                 int layoutRes, int position) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(context, parent, layoutRes);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.convertView = convertView;
        }
        viewHolder.position = position;//设置
        return viewHolder;
    }

    /**
     * 根据id获取item中控件
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int id) {
        T t = (T) mViews.get(id);
        if (t == null) {
            t = (T) convertView.findViewById(id);
            mViews.put(id, t);
        }
        return t;
    }

    /**
     * 获取当前Item
     *
     * @return
     */
    public View getItemView() {
        return convertView;
    }

    /**
     * 获取当前Item的位置
     *
     * @return
     */
    public int getItemPosition() {
        return position;
    }

    /**
     * 设置文字
     */
    public ViewHolder setText(int id, CharSequence text) {
        View view = getView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    /**
     * 设置图片
     */
    public ViewHolder setImageResource(int id, int drawableRes) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        } else {
            view.setBackgroundResource(drawableRes);
        }
        return this;
    }

    /**
     * 设置点击监听
     */
    public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }

    /**
     * 设置可见
     */
    public ViewHolder setVisibility(int id, int visible) {
        getView(id).setVisibility(visible);
        return this;
    }

    /**
     * 设置标签
     */
    public ViewHolder setTag(int id, Object obj) {
        getView(id).setTag(obj);
        return this;
    }

}
