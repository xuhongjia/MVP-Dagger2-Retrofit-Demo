package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.softstao.softstaolibrary.library.utils.LZImageLoadTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by jacob on 15/5/30.
 */
public class LZImageView extends ImageView {

    private LZImageLoadTool imageLoadTool;
    public LZImageView(Context context) {
        super(context);
        imageLoadTool = LZImageLoadTool.getInstance(context);
    }

    public LZImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        imageLoadTool = LZImageLoadTool.getInstance(context);
    }

    public LZImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        imageLoadTool = LZImageLoadTool.getInstance(context);
    }

    public void displayImage(String url) {
        imageLoadTool.loadImage(this,url, LZImageLoadTool.options);
    }

    public void displayImage(String url, DisplayImageOptions imageOptions) {
        imageLoadTool.loadImage(this, url,imageOptions);
    }
    
    public void dislayImageWithListener(String url,ImageLoadingListener listener)
    {
        imageLoadTool.loadImageFromUrl(this, url, LZImageLoadTool.options, listener);
    }
}
