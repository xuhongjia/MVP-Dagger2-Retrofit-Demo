package com.horry.mvp_dagger2_retrofit_demo.ui.activity.module;


import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.interactor.BaseInteractor;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.BaseViewer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/10.
 */
@Module
public class MainActivityModule {

    private MainActivity viewer;

    public MainActivityModule(MainActivity viewer) {
        this.viewer = viewer;
    }


    @Provides
    @ActivityScope
    BaseViewer provideViewer() {
        return viewer;
    }

    @Provides
    @ActivityScope
    BaseInteractor provideInteractor(){return new BaseInteractor();}

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(BaseViewer viewer, BaseInteractor interactor) {
        return new MainActivityPresenter(viewer, interactor);
    }


}
