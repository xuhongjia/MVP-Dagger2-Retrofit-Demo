package com.horry.mvp_dagger2_retrofit_demo.ui.activity.component;

import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.BaseActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.module.BaseActivityModule;

import dagger.Component;

/**
 * Created by xuhon on 2016/8/1.
 */
@ActivityScope
@Component(modules = BaseActivityModule.class,dependencies = AppComponent.class)
public interface BaseActivityComponent {
    void inject(BaseActivity baseActivity);
}
