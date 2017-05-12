package com.example.administrator.myapplication.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.ScreenUtil;

/**
 * Created by Administrator on 2017/5/12 0012.
 * 自定义模拟擦除妹子衣服控件
 */

public class StripMeizi extends View {

    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private Canvas mCanvas;
    private Bitmap mBeforeBitmap;
    private Bitmap mBackBitmap;

    private int mLastX, mLastY;
    private int screeW, screeH;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public StripMeizi(Context context) {
        super(context);
    }

    public StripMeizi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        screeW = ScreenUtil.getScreenW(context);
        screeH = ScreenUtil.getScreenH(context);
        init();

    }

    public StripMeizi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        //背后图片，设置全屏
        mBackBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.meizi_back);
        mBackBitmap = Bitmap.createScaledBitmap(mBackBitmap, screeW, screeH, false);

        mBeforeBitmap = Bitmap.createBitmap(screeW, screeH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBeforeBitmap);
        mCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.meizi_before), null,
                new RectF(0, 0, screeW, screeH), null);

        //画笔相关的设置
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(80);
    }

    private void drawPath() {
        mPaint.setXfermode(mXfermode);
        mCanvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackBitmap, 0, 0, null);
        drawPath();
        canvas.drawBitmap(mBeforeBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);
                mLastX = x;
                mLastY = y;
                break;
        }
        invalidate();
        return true;
    }

}
