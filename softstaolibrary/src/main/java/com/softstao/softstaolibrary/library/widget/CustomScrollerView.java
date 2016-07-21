package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * Created by Administrator on 2016/5/12.
 */
public class CustomScrollerView extends ScrollView {
    private long millis;
    private int downX;
    private int downY;
    private int mTouchSlop = 10;
    private OnScrollChangedListener onScrollChangedListener;
    public CustomScrollerView(Context context) {
        super(context);
    }

    public CustomScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        long now = System.currentTimeMillis();
        if(null != this.onScrollChangedListener) {
            this.onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
            if(now - this.millis > 1000L && this.getHeight() + oldt != this.getTotalVerticalScrollRange() && this.getHeight() + t == this.getTotalVerticalScrollRange()) {
                this.onScrollChangedListener.onScrollBottom();
                this.millis = now;
            }

            if(oldt != 0 && t == 0) {
                this.onScrollChangedListener.onScrollTop();
            }

        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                int moveX = (int) e.getRawY();
//                if(Math.abs(moveX - downX) > Math.abs(moveY - downY)){
//                    return super.onInterceptTouchEvent(e);
//                }
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    public OnScrollChangedListener getOnScrollChangedListener() {
        return this.onScrollChangedListener;
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    public int getTotalVerticalScrollRange() {
        return this.computeVerticalScrollRange();
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int var1, int var2, int var3, int var4);

        void onScrollTop();

        void onScrollBottom();
    }
}
