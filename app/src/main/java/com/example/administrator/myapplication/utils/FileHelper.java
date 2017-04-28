package com.example.administrator.myapplication.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class FileHelper {
    private Context context;

    public FileHelper() {
    }

    public FileHelper(Context context) {

        this.context = context;
    }

    /**
     * 保存文件
     *
     * @param fileName
     * @param fileContent
     * @throws IOException
     */
    public void save(String fileName, String fileContent) throws IOException {
        //使用私有模式,创建出来的文件只能被本应用访问,还会覆盖原文件
        FileOutputStream output = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        output.write(fileContent.getBytes());
        output.close();
    }

    /**
     * 读取文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public String read(String fileName) throws IOException {
        FileInputStream input = context.openFileInput(fileName);
        byte[] temp = new byte[1024];
        int lenth = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((lenth = input.read(temp)) > 0) {    //OutOfIndexException !=0
            stringBuilder.append(new String(temp, 0, lenth));
        }
        input.close();
        return stringBuilder.toString();
    }
}
