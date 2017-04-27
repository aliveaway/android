package com.example.administrator.myapplication.storage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.utils.FileHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件存贮
 */
public class StorageActivity extends BaseActivity {

    @BindView(R.id.write)
    Button write;

    @BindView(R.id.clear)
    Button clear;

    @BindView(R.id.editText)
    EditText txtSave;
    @BindView(R.id.file_content)
    EditText file_content;
    @BindView(R.id.re_read)
    Button re_read;

    private FileHelper fileHelper = new FileHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_storage);
    }

    @OnClick({R.id.clear, R.id.write, R.id.re_read})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.write:
                save(txtSave.getText().toString(), file_content.getText().toString());
                break;
            case R.id.clear:
                cleanTxt();
                break;
            case R.id.re_read:
                read(txtSave.getText().toString());
                break;
        }
    }

    private void read(String s) {
        try {
            String cont = fileHelper.read(s);
            Toast.makeText(this, "数据读取成功" + cont, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "数据读取失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void save(String fileName, String fileContent) {
        try {
            fileHelper.save(fileName, fileContent);
            Toast.makeText(this, "数据写入成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "数据写入失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void cleanTxt() {
        txtSave.setText("");
        file_content.setText("");
    }

}
