package com.example.administrator.myapplication.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class BitmapUtil {

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 计算宽高比
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        //原图的宽高
        final int width = options.outWidth;
        final int height = options.outHeight;
        int simapleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            final int heightRatio = Math.round((float) height / (float) reqHeight);

            simapleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

        }
        return simapleSize;
    }

}
