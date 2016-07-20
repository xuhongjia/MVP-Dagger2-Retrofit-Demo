package com.horry.mvp_dagger2_retrofit_demo;

import android.app.Application;
import android.content.Context;

import com.horry.mvp_dagger2_retrofit_demo.data.AppServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiComponent;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.data.api.DaggerApiComponent;

/**
 * Created by clevo on 2015/6/9.
 */
public class AppApplication  extends Application{

    private AppComponent appComponent;
    public static ApiComponent apiComponent;

    public static AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .appServiceModule(new AppServiceModule())
                .build();
        apiComponent = DaggerApiComponent.builder().apiServiceModule(new ApiServiceModule()).build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }

}
