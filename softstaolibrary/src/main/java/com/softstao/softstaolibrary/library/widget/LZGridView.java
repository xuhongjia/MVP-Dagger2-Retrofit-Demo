package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by jacob on 15/6/2.
 */
public class LZGridView extends GridView {
    public LZGridView(Context context) {
        super(context);
    }

    public LZGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LZGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
