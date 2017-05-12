package com.example.administrator.myapplication.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import butterknife.BindView;

public class CaClothes extends BaseActivity {

    @BindView(R.id.img_after)
    ImageView img_after;
    @BindView(R.id.img_before)
    ImageView img_before;

    private int[] imgIds = new int[]
            {
                    R.mipmap.pre1, R.mipmap.pre2, R.mipmap.pre3,
                    R.mipmap.pre4, R.mipmap.pre5, R.mipmap.pre6,
                    R.mipmap.pre7, R.mipmap.pre8, R.mipmap.pre9,
                    R.mipmap.pre10, R.mipmap.pre11, R.mipmap.pre12,
                    R.mipmap.pre13, R.mipmap.pre14, R.mipmap.pre15,
                    R.mipmap.pre16, R.mipmap.pre17, R.mipmap.pre18,
                    R.mipmap.pre19, R.mipmap.pre20, R.mipmap.pre21
            };

    private int[] imgIds2 = new int[]
            {
                    R.mipmap.after1, R.mipmap.after2, R.mipmap.after3,
                    R.mipmap.after4, R.mipmap.after5, R.mipmap.after6,
                    R.mipmap.after7, R.mipmap.after8, R.mipmap.after9,
                    R.mipmap.after10, R.mipmap.after11, R.mipmap.after12,
                    R.mipmap.after13, R.mipmap.after14, R.mipmap.after15,
                    R.mipmap.after16, R.mipmap.after17, R.mipmap.after18,
                    R.mipmap.after19, R.mipmap.after20, R.mipmap.after21
            };

    private int position;
    private Bitmap before;
    private Bitmap after;

    private Bitmap alterBitmap;

    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        position = Integer.parseInt(bundle.getString("num"));

        setUp();

    }

    private void setUp() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        before = BitmapFactory.decodeResource(getResources(), imgIds[position], options);
        after = BitmapFactory.decodeResource(getResources(), imgIds2[position], options);

        alterBitmap = Bitmap.createBitmap(before.getWidth(), before.getHeight(), Bitmap.Config.ARGB_4444);
        canvas = new Canvas(alterBitmap);
        paint = new Paint();
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        canvas.drawBitmap(before, new Matrix(), paint);
        img_after.setImageBitmap(after);
        img_before.setImageBitmap(before);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_ca_clothes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int newX = (int) event.getX();
                int newY = (int) event.getY();
                //setPixel方法是将某一个像素点设置成一个颜色，而这里我们把他设置成透明
                //另外通过嵌套for循环将手指触摸区域的20*20个像素点设置为透明
                for (int i = -20; i < 20; i++) {
                    for (int j = -20; j < 20; j++) {
                        if (i + newX >= 0 && j + newY >= 0 && i + newX < before.getWidth() && j + newY < before.getHeight())
                            alterBitmap.setPixel(i + newX, j + newY, Color.TRANSPARENT);
                    }
                }
                img_before.setImageBitmap(alterBitmap);
                break;
        }
        return true;
    }
}
