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

public abstract class BaseActivityModule<V extends BaseViewer,T extends BasePresenter<V>> {
    protected V viewer;
    protected T presenter;
    protected Class<T> tClass;
    protected Class<V> vClass;
    public BaseActivityModule(V viewer) {
        this.viewer = viewer;
        try {
            Constructor constructor =  tClass.getConstructor(vClass,Retrofit.class);
            presenter = (T) constructor.newInstance(viewer,((BaseActivity)viewer).retrofit);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
