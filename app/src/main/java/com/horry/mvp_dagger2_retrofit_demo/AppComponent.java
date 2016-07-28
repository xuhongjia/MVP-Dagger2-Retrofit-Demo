package com.horry.mvp_dagger2_retrofit_demo;

import android.app.Application;


import com.horry.mvp_dagger2_retrofit_demo.common.CookieManager;
import com.horry.mvp_dagger2_retrofit_demo.data.AppServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.model.User;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/9.
 */
@Singleton
@Component(modules = {AppModule.class, AppServiceModule.class, ApiServiceModule.class})
public interface AppComponent {

    Application getApplication();
    CookieManager getCookieManager();

    Retrofit getRetrofit();
    User getUser();
}
