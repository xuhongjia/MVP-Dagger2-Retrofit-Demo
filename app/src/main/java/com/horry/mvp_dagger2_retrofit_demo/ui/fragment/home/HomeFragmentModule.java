package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;

import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.module.BaseModule;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.HomeViewer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xuhon on 2016/8/3.
 */
@Module
public class HomeFragmentModule extends BaseModule<HomeFragment> {
    public HomeFragmentModule(HomeFragment viewer) {
        super(viewer);
    }
    @Provides
    @ActivityScope
    HomeFragment providerView() {
        return viewer;
    }
}
