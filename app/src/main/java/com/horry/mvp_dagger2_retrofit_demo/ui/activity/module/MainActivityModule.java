package com.horry.mvp_dagger2_retrofit_demo.ui.activity.module;


import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.BaseActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/10.
 */
@Module
public class MainActivityModule extends BaseActivityModule<MainActivity> {

    public MainActivityModule(MainActivity viewer) {
        super(viewer);
    }

    @Provides
    @ActivityScope
    MainActivityPresenter providePresenter() {
        return new MainActivityPresenter(viewer,viewer.retrofit);
    }
}
