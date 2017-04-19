package com.example.administrator.myapplication.component;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;

public class DateTimeDemoActivity extends BaseActivity implements DatePicker.OnDateChangedListener {

    @BindView(R.id.datePicker)
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDate();

    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, monthOfYear, dayOfMonth, this);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_date_time_demo);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(DateTimeDemoActivity.this, "您选择的日期是" + year + "年" + monthOfYear +
                "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
    }
}
