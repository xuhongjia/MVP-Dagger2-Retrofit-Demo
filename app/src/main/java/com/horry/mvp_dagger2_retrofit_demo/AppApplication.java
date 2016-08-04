package com.horry.mvp_dagger2_retrofit_demo;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.horry.mvp_dagger2_retrofit_demo.data.AppServiceModule;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiServiceModule;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/9.
 */
public class AppApplication  extends Application{
    public static DisplayImageOptions headOptions = new DisplayImageOptions
            .Builder()
//            .showImageOnLoading(R.mipmap.default_im)
//            .showImageOnFail(R.mipmap.default_im)
//            .showImageForEmptyUri(R.mipmap.default_im)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    private AppComponent appComponent;

    public static AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .appServiceModule(new AppServiceModule())
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(headOptions).build();
        ImageLoader.getInstance().init(configuration);
        enabledStrictMode();
        LeakCanary.install(this);
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
    private void enabledStrictMode() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
}
