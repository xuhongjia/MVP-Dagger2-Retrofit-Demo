package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;

import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;

import dagger.Component;

/**
 * Created by xuhon on 2016/8/3.
 */
@ActivityScope
@Component( dependencies = AppComponent.class)
public interface HomeFragementComponent {
    void inject(HomeFragment mainActivity);
    HomeFragmentPresenter presenter();
}
