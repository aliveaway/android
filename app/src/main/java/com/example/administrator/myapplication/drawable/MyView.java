package com.example.administrator.myapplication.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.BitmapUtil;

/**
 * Created by Administrator on 2017/5/4 0004.
 * 使用画笔 画面绘图
 */

public class MyView extends View {

    private Paint mPaint;
    private RectF arcRectF;
    private Bitmap bitmap;

    private RectF radioRect;//圆角矩形

    private Path mPath;//路径

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置画笔
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(36);     //字体大小，单位px
        mPaint.setStrokeWidth(5);   //粗细

        mPath = new Path();

        arcRectF = new RectF(0, 300, 200, 500);
        radioRect = new RectF(400, 10, 600, 80);

        bitmap = BitmapUtil.decodeSampledBitmapFromResource(getResources(), R.mipmap.meinv, 100, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.white));
        //参数 圆心左 圆心上 半径
        canvas.drawCircle(200, 200, 100, mPaint);   //圆形

        canvas.drawRect(10, 10, 200, 100, mPaint);

        //绘制bitmap
        canvas.drawBitmap(bitmap, 380, 0, mPaint);

        canvas.drawArc(arcRectF, 0, 90, true, mPaint);  //绘制弧形区域

        canvas.drawRoundRect(radioRect, 15, 15, mPaint);

        //绘制多边形
        drawMore(canvas, mPaint);

        //绘制文字
        drawText(canvas, mPaint);

    }

    private void drawText(Canvas canvas, Paint mPaint) {
        canvas.drawText("测试文字", 800, 50, mPaint);
    }

    private void drawMore(Canvas canvas, Paint mPaint) {
        mPath.moveTo(800, 800);
        mPath.lineTo(700, 700);
        mPath.lineTo(600, 600);
        mPath.lineTo(800, 800);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

}
