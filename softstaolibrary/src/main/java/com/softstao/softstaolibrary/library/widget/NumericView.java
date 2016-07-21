package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by jacob on 15/7/15.
 */
public class NumericView extends EditText implements TextWatcher {
    private String value = "";

    public NumericView(Context context) {
        super(context);
        addTextChangedListener(this);
    }

    public NumericView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(this);
    }

    public NumericView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int before,
                                  int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {

        int dotNum = 0;
        String string = editable.toString();
        for (int i = 0; i < string.length(); i++) {
            char mchar = string.charAt(i);
            if (i == 0 && mchar == '.') {
                editable.delete(0, 1);
                return;
            } else if (mchar == '.') {
                dotNum++;
                if (dotNum == 2) {
                    editable.delete(i, i + 1);
                    return;
                }
            }
        }

        if( string.length() > 2 && string.length() - string.indexOf('.') > 3 && string.contains("."))
        {
            editable.delete(string.length() -2,string.length() - 1);
        }

        if (!TextUtils.isEmpty(string)) {
            if(string.charAt(string.length() - 1) == '.')
            {
                string = string.substring(0,string.length() -1);
            }
            float interger = Float.valueOf(string);
            if (interger > 65536) {
                System.out.println("string =" + string);
                if(string.contains("."))
                {
                    editable.delete(string.indexOf(".") - 1, string.length()-1);
                }
                else
                    editable.delete(string.length() - 2, string.length()-1);

            }
        }
    }

    @Override
    public void onTextChanged(CharSequence string, int start, int end, int var4) {

    }
}
