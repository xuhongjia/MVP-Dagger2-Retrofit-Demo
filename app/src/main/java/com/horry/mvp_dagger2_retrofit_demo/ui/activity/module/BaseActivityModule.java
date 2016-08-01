package com.horry.mvp_dagger2_retrofit_demo.ui.activity.module;

import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.interactor.BaseInteractor;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.BasePresenter;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xuhon on 2016/8/1.
 */
@Module
public class BaseActivityModule {
    protected BaseViewer viewer;

    public BaseActivityModule(BaseViewer viewer) {
        this.viewer = viewer;
    }
}
