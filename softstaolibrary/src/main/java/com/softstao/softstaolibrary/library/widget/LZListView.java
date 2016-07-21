package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by jacob on 15/7/4.
 */
public class LZListView  extends ListView{
    public LZListView(Context context) {
        super(context);
    }

    public LZListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LZListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
