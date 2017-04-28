package com.example.administrator.myapplication.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/28 0028.
 * SD卡读取和保存文件
 */

public class SDFileHelper {
    private Context context;

    public SDFileHelper() {
    }

    public SDFileHelper(Context context) {

        this.context = context;
    }

    /**
     * 保存文件到SD卡
     *
     * @param fileName
     * @param fileContent
     */
    public void saveFileToSD(String fileName, String fileContent) throws IOException {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            fileName = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + fileName;
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            FileOutputStream out = new FileOutputStream(fileName);
            //将String字符串以字节流的形式写入到输出流中
            out.write(fileContent.getBytes());
            out.close();
        } else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }

    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public String readFromSD(String filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];

            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
        }
        return sb.toString();
    }

}
