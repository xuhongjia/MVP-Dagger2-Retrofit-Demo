package com.softstao.softstaolibrary.library.widget;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimeDisplayPicker extends TextView implements TimePickerDialog.OnTimeSetListener {

    private Context _context;

    private int hour = 25;
    private int minute;

    public TimeDisplayPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _context = context;
    }

    public TimeDisplayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        setAttributes();
    }

    public TimeDisplayPicker(Context context) {
        super(context);
        _context = context;
        setAttributes();
    }

    private void getTime()
    {
        String[] tokensVal = getText().toString().split(":");
        if(tokensVal != null && tokensVal.length > 1)
        {
            hour = Integer.parseInt(tokensVal[0]);
            minute = Integer.parseInt(tokensVal[1]);
        }

    }

    private void setAttributes() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime();
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        final Calendar c = Calendar.getInstance();
        TimePickerDialog timePicker;
        if(hour == 25)
        {
            timePicker = new TimePickerDialog(_context,TimePickerDialog.THEME_HOLO_DARK,this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true);
        }
        else
        {
            timePicker = new TimePickerDialog(_context,TimePickerDialog.THEME_HOLO_DARK,this,hour,minute,true);

        }

        timePicker.show();

    }



    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        setText( hour + ":" + minute);
    }
}