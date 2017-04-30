package com.example.administrator.myapplication.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button img_capture;

    static ByteArrayOutputStream byteOut = null;
    private Bitmap bitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
