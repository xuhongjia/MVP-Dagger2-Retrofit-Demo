package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;


import com.horry.mvp_dagger2_retrofit_demo.AppApplication;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BasePresenter {

    @Inject
    protected Retrofit retrofit;
    public BasePresenter(){
        AppApplication.apiComponent.inject(this); ;
    }
}
