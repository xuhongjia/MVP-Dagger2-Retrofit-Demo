package com.horry.mvp_dagger2_retrofit_demo.ui.activity.main;



import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;

import dagger.Component;

/**
 * Created by clevo on 2015/6/10.
 */
@ActivityScope
@Component(modules = MainActivityModule.class , dependencies = AppComponent.class)
public interface MainActivityComponent{
    void inject(MainActivity mainActivity);
    MainActivityPresenter presenter();
}
