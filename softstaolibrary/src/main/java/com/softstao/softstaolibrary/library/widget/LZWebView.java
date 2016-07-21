package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jacob on 15/6/24.
 */
public class LZWebView extends WebView {

    public LZWebView(Context context) {
        super(context);

        initView();
    }

    public LZWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LZWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportMultipleWindows(true);
        getSettings().setDefaultTextEncodingName("UTF-8");
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }
}
