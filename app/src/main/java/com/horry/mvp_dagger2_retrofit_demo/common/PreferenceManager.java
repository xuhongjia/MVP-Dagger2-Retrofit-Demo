package com.horry.mvp_dagger2_retrofit_demo.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2016/7/20.
 */
public class PreferenceManager {
    private static PreferenceManager _PreferenceManager;
    private SharedPreferences preferences;
    private PreferenceManager(Application application){
        PackageManager pm = application.getPackageManager();
        preferences= application.getSharedPreferences(application.getApplicationInfo().loadLabel(pm).toString(),Context.MODE_PRIVATE);
    }

    public static SharedPreferences getDefaultSharedPreferences(Application application){
        if(_PreferenceManager==null){
            _PreferenceManager = new PreferenceManager(application);
        }
        return _PreferenceManager.getPreferences();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }
}
