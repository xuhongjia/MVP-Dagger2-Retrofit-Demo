package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.softstao.softstaolibrary.R;


/**
 * Created by jacob on 15/6/28.
 */
public class EmptyLayout extends LinearLayout {
    private Context mContext;
    public EmptyLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initView();
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView()
    {
        View view = View.inflate(mContext, R.layout.layout_empty, this);

    }

    public void setEmptyLayout(int layoutId)
    {
        this.removeAllViews();
        View.inflate(mContext,layoutId, this);
    }

    public void setBackground(int imageId)
    {
        this.removeAllViews();
        setBackgroundResource(imageId);
    }
}
