package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by xuhon on 2016/8/3.
 */
public class HomeFragmentPresenter extends BasePresenter<HomeFragment> {
    /**
     * 构造方法
     *
     * @param viewer
     * @param apiService
     */
    @Inject
    public HomeFragmentPresenter(HomeFragment viewer, ApiService apiService) {
        super(viewer, apiService);
    }

    public void getHome(){

    }

    public void getMember(){

    }
}
