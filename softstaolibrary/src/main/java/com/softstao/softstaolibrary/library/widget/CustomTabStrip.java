package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gigamole.library.navigationtabstrip.NavigationTabStrip;

/**
 * Created by Administrator on 2016/6/3.
 */
public class CustomTabStrip extends NavigationTabStrip {
    private TabStripOnClick stripOnClick;
    public CustomTabStrip(Context context) {
        super(context);
    }

    public CustomTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(stripOnClick!=null){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stripOnClick.onClick(getTabIndex());
                    }
                },200);
            }
        }
        return super.onTouchEvent(event);
    }

    public interface TabStripOnClick{
        void onClick(int index);
    }

    public TabStripOnClick getStripOnClick() {
        return stripOnClick;
    }

    public void setStripOnClick(TabStripOnClick stripOnClick) {
        this.stripOnClick = stripOnClick;
    }
}
