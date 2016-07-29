package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jacob on 15/6/16.
 */
public class UploadImageView extends LZImageView {
    public UploadImageView(Context context) {
        super(context);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public UploadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UploadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnUploadListener
    {
        void onUploadSuccess();
        void onUploadFailed();
    }

}
