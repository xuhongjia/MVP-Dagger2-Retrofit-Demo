package com.horry.mvp_dagger2_retrofit_demo.data.api;

import android.app.Application;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.horry.mvp_dagger2_retrofit_demo.common.CookieManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by clevo on 2015/6/10.
 */
@Module
public class ApiServiceModule {
    private static final int DEFAULT_TIMEOUT = 5;
    private static final String ENDPOINT="http://www.baidu.com/";


    @Provides @Singleton
    OkHttpClient provideClient(CookieManager cm){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cookieJar(new CookieManager());
        return client.build();
    }


    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }
    @Provides
    @Singleton
    CookieManager provideCookieManager(){
        return new CookieManager();
    }

}
