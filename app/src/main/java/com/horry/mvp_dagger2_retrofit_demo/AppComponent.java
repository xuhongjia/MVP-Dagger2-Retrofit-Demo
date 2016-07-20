package com.horry.mvp_dagger2_retrofit_demo;

import android.app.Application;


import com.horry.mvp_dagger2_retrofit_demo.data.AppServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.model.User;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by clevo on 2015/6/9.
 */
@Singleton
@Component(modules = {AppModule.class, AppServiceModule.class})
public interface AppComponent {

    Application getApplication();

    User getUser();
}
