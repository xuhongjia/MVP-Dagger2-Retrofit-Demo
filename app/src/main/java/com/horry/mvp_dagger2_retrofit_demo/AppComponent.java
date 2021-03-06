package com.horry.mvp_dagger2_retrofit_demo;


import com.horry.mvp_dagger2_retrofit_demo.data.AppServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.global.SharePreferenceManager;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/9.
 */
@Singleton
@Component(modules = {AppModule.class, AppServiceModule.class, ApiServiceModule.class})
public interface AppComponent {
    void inject(BaseViewer viewer);
    AppApplication getApplication();
//    CookieManager getCookieManager();
    ApiService getApiService();
    Retrofit getRetrofit();
    SharePreferenceManager sharePreferenceManager();
    UserManager userManager();

}
