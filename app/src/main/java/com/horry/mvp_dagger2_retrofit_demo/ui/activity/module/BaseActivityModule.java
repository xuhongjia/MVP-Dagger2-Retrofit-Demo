package com.horry.mvp_dagger2_retrofit_demo.ui.activity.module;

import com.horry.mvp_dagger2_retrofit_demo.ui.activity.BaseActivity;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import java.lang.reflect.Constructor;

import dagger.Module;
import retrofit2.Retrofit;

/**
 * Created by xuhon on 2016/8/1.
 */
public abstract class BaseActivityModule<V extends BaseViewer> {
    protected V viewer;
    public BaseActivityModule(V viewer) {
        this.viewer = viewer;
    }

}
