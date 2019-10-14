package com.example.arun.restorenttablebookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DateTime extends AppCompatActivity {

    TimePicker mTime;
    DatePicker mDate;
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        mTime = findViewById(R.id.mTimeTime);
        mDate = findViewById(R.id.mTimeDate);
        mBtn = findViewById(R.id.mTimeBtn);


        mTime.setIs24HourView(true);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NumberFormat numberFormat = new DecimalFormat("00");
                String time = String.valueOf(mTime.getCurrentHour());

                String dateDay = String.valueOf(mDate.getDayOfMonth());
                String dateMonth = String.valueOf(mDate.getMonth() + 1);

                Intent i = new Intent(getApplicationContext(), BookingActivity.class);
                i.putExtra("time", time);
                i.putExtra("dateDay", dateDay);
                i.putExtra("dateMonth", dateMonth);
                i.putExtra("timeMin", String.valueOf(mTime.getCurrentMinute()));

                startActivity(i);
            }
        });
    }
}
