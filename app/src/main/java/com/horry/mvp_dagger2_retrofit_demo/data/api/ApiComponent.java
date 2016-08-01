package com.horry.mvp_dagger2_retrofit_demo.data.api;

import com.horry.mvp_dagger2_retrofit_demo.common.CookieManager;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/7/20.
 */
@Singleton
@Component(modules = ApiServiceModule.class)
public interface ApiComponent {

    CookieManager getCookieManager();

    Retrofit getRetrofit();
}
