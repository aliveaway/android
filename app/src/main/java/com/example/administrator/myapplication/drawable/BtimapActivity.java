package com.example.administrator.myapplication.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class BtimapActivity extends BaseActivity {

    @BindView(R.id.capture_btn)
    Button capture_btn;

    @BindView(R.id.img_capture)
    ImageView img_capture;

    static ByteArrayOutputStream byteOut = null;
    private Bitmap bitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setBitmapFromResources();

    }

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
     * 计算宽高比率
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //获取APP最大分配内存
    private void getMaxMem() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
    }

    private void getImgInfo(int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, options);
        int imgWidth = options.outWidth;
        int imgHeight = options.outHeight;
        String imgType = options.outMimeType;
        Log.i("TAG", "图片宽度" + imgWidth);
        Log.i("TAG", "图片高度" + imgHeight);
        Log.i("TAG", "图片类型" + imgType);
    }


    private void setBitmapFromResources() {
//        final View contentView = getWindow().getDecorView();
        Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.meinv,
                img_capture.getWidth(), img_capture.getHeight());
        img_capture.setImageBitmap(b);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_btimap);
    }

    @OnClick(R.id.capture_btn)
    public void captureScreen() {

        Runnable action = new Runnable() {
            @Override
            public void run() {
                try {
                    //1.获取要截屏的布局
                    final View contentView = getWindow().getDecorView();
                    //2.创建bitmap,大小和contentView一样
                    bitmap = Bitmap.createBitmap(contentView.getWidth(), contentView.getHeight(),
                            Bitmap.Config.ARGB_4444);
                    contentView.draw(new Canvas(bitmap));
                    //3.把bitmap压入输出流
                    ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOutput);
                    //4.保存图片
                    savePic(bitmap, "sdcard/short.png");
                } catch (Exception e) {
                    e.printStackTrace();

                    try {
                        if (null != byteOut)
                            byteOut.close();

                        if (null != bitmap && !bitmap.isRecycled()) {
                            bitmap.recycle();
                            bitmap = null;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        action.run();
    }

    private void savePic(Bitmap bitmap, String s) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(s);
            if (null != fileOutputStream) {
                boolean success = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                if (success)
                    Toast.makeText(this, "截屏成功", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        img_capture.srcCompat();
        if (null != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        img_capture.setImageBitmap(null);
    }
}
