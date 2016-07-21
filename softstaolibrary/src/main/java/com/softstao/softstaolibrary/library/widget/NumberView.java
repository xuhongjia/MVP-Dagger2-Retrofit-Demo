package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softstao.softstaolibrary.R;


/**
 * Created by apple2310 on 15/4/2.
 */
public class NumberView extends LinearLayout {

    private TextView decreaceView;
    private EditText numberView;
    private TextView increaceView;
    private Context mContext;
    private int number;

    private OnNumberButtonClickListener listener;

    public NumberView(Context context) {
        super(context);
        mContext = context;

        initView();
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initView();
    }

    public int getNumber() {

        number = Integer.valueOf(numberView.getText().toString());

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.numberView.setText(number + "");
    }

    public void setListener(OnNumberButtonClickListener listener) {
        this.listener = listener;
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.number_layout, this, true);

        decreaceView = (TextView) findViewById(R.id.decreace_btn);
        decreaceView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                number = Integer.valueOf(numberView.getText().toString());
                int preNumber = number;
                if (number > 1) {
                    number--;
                } else {
                    number = 1;
                }
                numberView.setText(number + "");
                if (listener != null)
                    listener.onDecreaseButtonClick(numberView, number,preNumber);
            }
        });
        increaceView = (TextView) findViewById(R.id.increase_btn);
        increaceView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                number = Integer.valueOf(numberView.getText().toString());
                number++;

                numberView.setText(number + "");
                if (listener != null)
                    listener.onIncreaseButtonClick(numberView, number);

            }
        });
        numberView = (EditText) findViewById(R.id.number);

    }

    public interface OnNumberButtonClickListener {
        void onDecreaseButtonClick(View view, int number, int preNumber);

        void onIncreaseButtonClick(View view, int number);

    }

}