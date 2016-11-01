package com.horry.mvp_dagger2_retrofit_demo;

import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

/**
 * Created by xuhon on 2016/8/1.
 */
public abstract class BaseModule<V extends BaseViewer> {
    protected V viewer;
    public BaseModule(V viewer) {
        this.viewer = viewer;
    }
}
