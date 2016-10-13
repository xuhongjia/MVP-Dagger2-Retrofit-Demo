package com.softstao.softstaolibrary.library.upyun.common;


import com.softstao.softstaolibrary.library.upyun.listener.UpProgressListener;

import okhttp3.RequestBody;


public class ProgressHelper {
    public ProgressHelper() {
    }

    public static ProgressRequestBody addProgressListener(RequestBody requestBody, UpProgressListener progressRequestListener) {
        return new ProgressRequestBody(requestBody, progressRequestListener);
    }
}

