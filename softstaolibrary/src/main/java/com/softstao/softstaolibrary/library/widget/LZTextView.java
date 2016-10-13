package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.softstao.softstaolibrary.R;


/**
 * Created by jacob on 15/6/1.
 */
public class LZTextView extends TextView {

    private boolean deleteLine;
    private boolean underLine;
    public LZTextView(Context context) {
        super(context);
    }

    public LZTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public LZTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LZTextView, defStyleAttr, 0);
        deleteLine = a.getBoolean(R.styleable.LZTextView_deleteLine, deleteLine);
        underLine = a.getBoolean(R.styleable.LZTextView_underLine,underLine);
        a.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(deleteLine)
        {
           getPaint().setFlags(getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  //添加删除线

        }
        if(underLine)
        {
            getPaint().setFlags(getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  //添加下划线
        }
    }
}
