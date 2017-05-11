package com.example.administrator.myapplication.drawable;

import android.content.Context;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.administrator.myapplication.base.CommonAdapter;
import com.example.administrator.myapplication.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MeziAdapter extends CommonAdapter<Integer> {

    private Context mContext;

    public MeziAdapter(Context context, List<Integer> mData, int layoutId) {
        super(context, mData, layoutId);
        this.mContext = context;
    }

    @Override
    public void bindView(ViewHolder viewHolder, Integer obj) {
        ImageView img = new ImageView(this.mContext);
        img.setImageResource(obj);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setLayoutParams(new Gallery.LayoutParams(250, 250));
        /*TypedArray typedArray = mContext.obtainStyledAttributes(R.styleable.);
        img.setBackgroundResource(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));*/
    }
}
