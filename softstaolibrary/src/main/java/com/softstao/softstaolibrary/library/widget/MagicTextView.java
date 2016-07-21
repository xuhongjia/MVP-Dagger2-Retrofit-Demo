package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jacob on 15/4/30.
 */
public class MagicTextView extends TextView {

    double curentMoney = 0;
    double value = 0;
    double step;
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(curentMoney + step < value)
            {
                curentMoney += step;
                setText("￥" + String.format("%.2f",curentMoney));
                mHandler.sendEmptyMessageDelayed(0,20);
            }
            else
            {
                setText("￥" + String.format("%.2f",value));
            }

        }
    };
    public MagicTextView(Context context) {
        super(context);
    }

    public MagicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MagicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(double value)
    {
        this.value = value;

        step = value / 50;

        mHandler.sendEmptyMessageDelayed(0,20);
    }
}
