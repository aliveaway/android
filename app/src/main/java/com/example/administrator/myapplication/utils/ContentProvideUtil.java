package com.example.administrator.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/4/25 0025.
 * 内容提供者工具类
 */

public class ContentProvideUtil {
    public static final int WRITE_REQUEST_CODE = 2002;

    /*private Context mContext;

    private ContentProvideUtil(Context context) {
        this.mContext = context;
    }

    public ContentProvideUtil getInstance(Context context) {
        return new ContentProvideUtil(context);
    }*/

    /**
     * 根据Uri获取文件参数
     * 文件名和文件大小
     * * @param uri
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void dumpImageMetaData(Uri uri, Context context) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.e(TAG, "Display Name: " + displayName);
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                String size = null;
                if (!cursor.isNull(sizeIndex)) {
                    size = cursor.getString(sizeIndex);
                } else {
                    size = "Unknown";
                }
                Log.e(TAG, "Size: " + size + "byte");
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 根据Uri获取Bitmap
     *
     * @param uri
     * @return
     * @throws IOException
     */
    public static Bitmap getBitmapFromUri(Uri uri, Context context) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
        assert parcelFileDescriptor != null;
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    /**
     * 根据Uri获取输入流
     *
     * @param uri
     * @return
     * @throws IOException
     */
    public String readTextFromUri(Uri uri, Context context) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        inputStream.close();
//        parcelFileDescriptor.close();
        return stringBuilder.toString();
    }

    /**
     * 创建新文件以及删除文件
     *
     * @param mimeType
     * @param fileName
     */
    public static void createFile(String mimeType, String fileName, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        activity.startActivityForResult(intent, WRITE_REQUEST_CODE);
    }

}
