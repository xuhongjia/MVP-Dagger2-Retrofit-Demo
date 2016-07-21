package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class RadomLinnerLayout extends ViewGroup {

    private static final int PADDING_HOR = 50;// 水平方向padding
    private static final int PADDING_VERTICAL = 20;// 垂直方向padding
    private static final int SIDE_MARGIN = 10;//两边间距
    private static final int HORIZONTAL_SPACE = 25; //2个子view之间的距离
    private static final int VERTICAL_SPACE = 25; //2个子view之间的距离

    private Context mContext;


    public RadomLinnerLayout(Context context) {

        super(context);
        mContext = context;

    }

    public RadomLinnerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;// 横坐标
        int y = 0;// 纵坐标
        int rows = 1;// 总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - SIDE_MARGIN * 2;// 实际宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
//            child.setPadding(PADDING_HOR, PADDING_VERTICAL, PADDING_HOR,
//                    PADDING_VERTICAL);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + HORIZONTAL_SPACE;
            if (x > actualWidth) {// 换行
                x = width;
                rows++;
            }
            y = rows * (height + HORIZONTAL_SPACE);
        }
        setMeasuredDimension(actualWidth, y);
    }


    @Override
    protected  void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = SIDE_MARGIN;// 横坐标开始
        int y = 0;// 纵坐标开始
        int rows = 1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            if(i == 0)
            {
                x += width;

            }
            else
            {
                x += width + HORIZONTAL_SPACE;
            }
            if (x > autualWidth) {
                x = width + SIDE_MARGIN;
                rows++;
            }
            y = rows * (height + HORIZONTAL_SPACE);
            if (i == 0) {
                view.layout(x - width, y - height, x
                        , y);
            } else {
                view.layout(x - width, y - height, x, y);
            }
        }

    }
}
