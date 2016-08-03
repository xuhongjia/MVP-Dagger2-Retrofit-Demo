package com.horry.mvp_dagger2_retrofit_demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/9.
 */
@Module
public class AppModule {

    private AppApplication application;

    public AppModule(AppApplication application){
        this.application=application;
    }

    @Provides
    @Singleton
    public AppApplication provideApplication(){
        return application;
    }

}
