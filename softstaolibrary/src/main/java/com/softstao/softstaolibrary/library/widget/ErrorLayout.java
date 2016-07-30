package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softstao.softstaolibrary.R;

/**
 * Created by xuhon on 2016/7/29.
 */
public class ErrorLayout extends RelativeLayout {
    private Context mContext;
    private ImageView errorImg;
    private TextView errorText;
    public ErrorLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public ErrorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public ErrorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){
        View view = View.inflate(mContext, R.layout.error_layout, this);
        errorImg = (ImageView) view.findViewById(R.id.error_img);
        errorText = (TextView) view.findViewById(R.id.error_text);
    }

    public void setErrorImage(Drawable drawable){
        if(errorImg!=null){
            errorImg.setImageDrawable(drawable);
        }
    }

    public void setErrorMsg(String errorMsg){
        if(errorText!=null){
            errorText.setText(errorMsg);
        }
    }
}
