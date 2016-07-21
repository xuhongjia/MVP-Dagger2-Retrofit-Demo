package com.softstao.softstaolibrary.library.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.softstao.softstaolibrary.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDisplayPicker extends TextView implements DatePickerDialog.OnDateSetListener {

    private Context _context;
    private int year;
    private int month;
    private int day;

    public DateDisplayPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _context = context;
    }

    public DateDisplayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        setAttributes();
    }

    public DateDisplayPicker(Context context) {
        super(context);
        _context = context;
        setAttributes();
    }

    private void setAttributes() {

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
                showDateDialog();
            }
        });
    }

    private void getDate() {
        String dateStr = getText().toString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            month = Integer.parseInt(android.text.format.DateFormat.format("MM", date).toString());
            year = Integer.parseInt(android.text.format.DateFormat.format("yyyy", date).toString());
            day = Integer.parseInt(android.text.format.DateFormat.format("dd", date).toString());
        } catch (ParseException e) {
            year = 0;
            month = 0;
            day = 0;
        }
    }

    private void showDateDialog() {
        final Calendar c = Calendar.getInstance();
        DatePickerDialog dp;
        if (year == 0) {
            dp = new DatePickerDialog(new ContextThemeWrapper(_context,
                    R.style.DatePickerDialog), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        } else {
            dp = new DatePickerDialog(new ContextThemeWrapper(_context,
                    R.style.DatePickerDialog), this, year, month - 1, day);
        }

        dp.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        setText(String.format("%s-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
    }
}