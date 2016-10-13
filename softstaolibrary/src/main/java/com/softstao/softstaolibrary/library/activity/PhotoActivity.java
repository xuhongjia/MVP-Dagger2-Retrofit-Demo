package com.softstao.softstaolibrary.library.activity;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.softstao.softstaolibrary.R;
import com.softstao.softstaolibrary.library.widget.LZToast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends AppCompatActivity {
    private TextView mCurrMatrixTv;
    private PhotoViewAttacher mAttacher;
    private Matrix mCurrentDisplayMatrix = null;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        url = getIntent().getStringExtra("url");
        if(url==null) {
            LZToast.getInstance(this).showToast("图片地址错误");
            finish();
        }
        ImageView mImageView = (ImageView) findViewById(R.id.iv_photo);
//        mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        Glide.with(this).load(url).fitCenter().error(R.mipmap.img_error).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
        // The MAGIC happens here!
        mAttacher = new PhotoViewAttacher(mImageView);
        mCurrMatrixTv = (TextView) findViewById(R.id.tv_current_matrix);
        // Lets attach some listeners, not required though!
//        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
        mAttacher.setOnSingleFlingListener(new SingleFlingListener());
        setMiuiStatusBarDarkMode(true);
    }

    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
            float xPercentage = x * 100f;
            float yPercentage = y * 100f;
            finish();
//            showToast(String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage, view == null ? 0 : view.getId()));
        }

        @Override
        public void onOutsidePhotoTap() {
//            showToast("You have a tap event on the place where out of the photo.");
        }
    }

    private class MatrixChangeListener implements PhotoViewAttacher.OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {
//            mCurrMatrixTv.setText(rect.toString());
        }
    }

    private class SingleFlingListener implements PhotoViewAttacher.OnSingleFlingListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            if (BuildConfig.DEBUG) {
//                Log.d("PhotoView", String.format(FLING_LOG_STRING, velocityX, velocityY));
//            }
            return true;
        }
    }

    public boolean setMiuiStatusBarDarkMode(boolean darkmode) {
        Class<? extends Window> clazz = getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
