package com.softstao.softstaolibrary.library.upyun.listener;

public interface UpProgressListener {
    void onRequestProgress(long bytesWrite, long contentLength);
}
