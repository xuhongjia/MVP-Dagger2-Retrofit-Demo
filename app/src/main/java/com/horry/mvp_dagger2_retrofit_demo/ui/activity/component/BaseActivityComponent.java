package com.horry.mvp_dagger2_retrofit_demo.ui.activity.component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.BaseActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.module.BaseActivityModule;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import dagger.Component;

/**
 * Created by xuhon on 2016/8/1.
 */

public interface BaseActivityComponent {
    void inject(BaseActivity baseActivity);
    BasePresenter presenter();
}
