package com.horry.mvp_dagger2_retrofit_demo.ui.activity.module;


import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.BaseActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.interactor.BaseInteractor;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
@Module
public class MainActivityModule extends BaseActivityModule<MainViewer,MainActivityPresenter>{

    public MainActivityModule(MainViewer viewer) {
        super(viewer);
    }

    @Provides
    @ActivityScope
    BasePresenter providePresenter() {
        return presenter;
    }
}
