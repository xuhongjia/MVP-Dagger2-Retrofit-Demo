package com.horry.mvp_dagger2_retrofit_demo.ui.activity.component;



import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.BaseActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.module.MainActivityModule;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
@ActivityScope
@Component(modules = MainActivityModule.class , dependencies = AppComponent.class)
public interface MainActivityComponent{
    void inject(MainActivity mainActivity);
    MainActivityPresenter presenter();
}
