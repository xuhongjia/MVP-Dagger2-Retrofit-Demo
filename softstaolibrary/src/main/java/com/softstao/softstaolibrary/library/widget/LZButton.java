package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.Button;

import com.softstao.softstaolibrary.R;

/**
 * Created by jacob on 15/6/15.
 */
public class LZButton extends Button {
    private int backgroundColor = 0xfff;
    private int radius = 5;

    public LZButton(Context context) {
        super(context);
    }

    public LZButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LZButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LZTextView, defStyleAttr, 0);
        radius = a.getInteger(R.styleable.LZButton_lz_radius, radius);
        backgroundColor = a.getColor(R.styleable.LZButton_lz_background, backgroundColor);
        a.recycle();

        float[] outerRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape roundRectShape = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
        bottomShapeDrawable.getPaint().setColor(backgroundColor);

        setBackgroundDrawable(bottomShapeDrawable);
    }


}
