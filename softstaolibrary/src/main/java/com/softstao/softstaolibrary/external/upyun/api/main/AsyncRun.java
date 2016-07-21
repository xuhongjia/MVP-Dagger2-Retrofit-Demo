package com.softstao.softstaolibrary.external.upyun.api.main;

import android.os.Handler;
import android.os.Looper;

public class AsyncRun {
    public static void run(Runnable r) {
        Handler h = new Handler(Looper.getMainLooper());
        h.post(r);
    }
}