package com.horry.mvp_dagger2_retrofit_demo.data;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.global.SharePreferenceManager;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import javax.inject.Inject;

/**
 * Created by xuhon on 2016/10/13.
 */
public abstract class ModelHelper {
    protected SharePreferenceManager sharePreferenceManager;
    protected UserManager userManager;
    protected ApiService apiService;

    public ModelHelper(SharePreferenceManager sharePreferenceManager, UserManager userManager, ApiService apiService){
        this.sharePreferenceManager=sharePreferenceManager;
        this.userManager=userManager;
        this.apiService=apiService;
    }
}
