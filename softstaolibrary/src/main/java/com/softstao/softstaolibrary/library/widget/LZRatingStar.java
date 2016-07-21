package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.softstao.softstaolibrary.R;

/**
 * Created by apple2310 on 15/2/26.
 */
public class LZRatingStar extends LinearLayout {

    private Context mContext;
    private int rating = 3;
    private int ratingIcon = R.drawable.comment_star;

    public LZRatingStar(Context context) {
        super(context);
        mContext = context;
    }

    public LZRatingStar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public LZRatingStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyRatingStar, defStyleAttr, 0);

        rating = a.getInteger(R.styleable.MyRatingStar_rateValue,rating);
        ratingIcon = a.getResourceId(R.styleable.MyRatingStar_rateIcon,ratingIcon);

        a.recycle();

        init();
    }

    private void init() {

        this.setOrientation(HORIZONTAL);

        if (rating != 0) {
            setRating(rating);
        }
    }

    public void setRating(int rating) {
        this.rating = rating;

        removeAllViews();

        for (int i = 0; i < rating; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(ratingIcon);
            LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params1.rightMargin = 2;
            params1.leftMargin = 0;
            params1.rightMargin =0;
            imageView.setLayoutParams(params1);

            addView(imageView);
        }

    }
}
