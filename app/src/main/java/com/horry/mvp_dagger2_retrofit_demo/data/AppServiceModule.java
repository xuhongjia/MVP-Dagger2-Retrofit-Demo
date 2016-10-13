package com.horry.mvp_dagger2_retrofit_demo.data;


import com.google.gson.Gson;
import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.global.SharePreferenceManager;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
import com.horry.mvp_dagger2_retrofit_demo.model.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/13.
 */
@Module
public class AppServiceModule {

    @Provides
    @Singleton
    SharePreferenceManager provideSharePreferenceManager(AppApplication appApplication, Gson gson){
        return new SharePreferenceManager(appApplication,gson);
    }

    @Provides
    @Singleton
    UserManager provideUserManager(SharePreferenceManager sharePreferenceManager){
        return new UserManager(sharePreferenceManager);
    }
}
