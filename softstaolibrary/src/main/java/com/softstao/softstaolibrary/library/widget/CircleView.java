package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.softstao.softstaolibrary.R;

/**
 * Created by Shahab on 3/20/14.
 */
public class CircleView
        extends View
{

    private int circleRadius = 20;
    private int strokeColor = 0xFFFF8C00;
    private int strokeWidth = 15;
    private int fillColor = 0XFFFFAB00;
    private int circleGap = 20;
    private String text = "1";
    private int textColor = 0XFFFFFFFF;
    private int textSize = 14;
    private boolean empty = false;

    private final Rect textBounds = new Rect(); //don't new this up in a draw method


    public CircleView(Context context) {
        super(context);

        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray aTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);

        strokeColor = aTypedArray.getColor(R.styleable.CircleView_circleStrokeColor, strokeColor);
        strokeWidth = aTypedArray.getDimensionPixelSize(R.styleable.CircleView_circleStrokeWidth, strokeWidth);
        fillColor = aTypedArray.getColor(R.styleable.CircleView_circleFillColor, fillColor);
        circleRadius = aTypedArray.getDimensionPixelSize(R.styleable.CircleView_circleRadius, circleRadius);
        circleGap = aTypedArray.getDimensionPixelSize(R.styleable.CircleView_circleGap, circleGap);
        text = aTypedArray.getString(R.styleable.CircleView_text);
        textColor = aTypedArray.getColor(R.styleable.CircleView_textColor,textColor);
        textSize = aTypedArray.getInteger(R.styleable.CircleView_textSize,textSize);
        empty = aTypedArray.getBoolean(R.styleable.CircleView_empty,false);
        aTypedArray.recycle();

        init();
    }

    public CircleView(Context context, int strokeColor, int strokeWidth, int fillColor, int circleRadius, int circleGap) {
        super(context);
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.fillColor = fillColor;
        this.circleRadius = circleRadius;
        this.circleGap = circleGap;

        init();
    }

    private void init() {
        this.setMinimumHeight(circleRadius * 2 + strokeWidth);
        this.setMinimumWidth(circleRadius * 2 + strokeWidth);
        this.setSaveEnabled(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        int ox = w/2;
        int oy = h/2;

        if(empty)
        {
            canvas.drawCircle(ox, oy, circleRadius - strokeWidth, getStroke());
        }
        else
        {
            canvas.drawCircle(ox, oy, circleRadius, getFill());
        }


        drawTextCentred(canvas, getFontPaint(), text, canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    private Paint getStroke()
    {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(strokeWidth);
        p.setColor(strokeColor);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);

        return p;
    }

    private Paint getFill()
    {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(fillColor);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        return p;
    }

    private Paint getFontPaint()
    {
        Paint textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
//        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(circleRadius);

        return textPaint;
    }


    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        invalidate();
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        invalidate();
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
        invalidate();
    }

    public int getCircleGap() {
        return circleGap;
    }

    public void setCircleGap(int circleGap) {
        this.circleGap = circleGap;
        invalidate();
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
        invalidate();
    }

    public boolean isEmpty() {
        return empty;
    }

    public String getText() {
        return text;
    }

    public Rect getTextBounds() {
        return textBounds;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy){
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), circleRadius),
                getDefaultSize(getSuggestedMinimumHeight(), circleRadius));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }
}