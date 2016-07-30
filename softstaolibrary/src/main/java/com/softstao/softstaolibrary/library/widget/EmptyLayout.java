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
 * Created by horry
 */
public class EmptyLayout extends RelativeLayout {
    private Context mContext;
    private ImageView emptyImg;
    private TextView emptyText;
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

    private void initView() {
        View view = View.inflate(mContext, R.layout.empty_layout, this);
        emptyText = (TextView) view.findViewById(R.id.empty_text);
        emptyImg = (ImageView) view.findViewById(R.id.empty_img);
    }

    public void setImageSrc(Drawable drawable){
        if(emptyImg!=null){
            emptyImg.setImageDrawable(drawable);
        }
    }

    public void setEmptyText(String emptyMsg){
        if(emptyText!=null){
            emptyText.setText(emptyMsg);
        }
    }
}
