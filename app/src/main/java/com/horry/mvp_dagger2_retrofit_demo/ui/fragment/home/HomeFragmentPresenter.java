package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.model.Response;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

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

    public void getHome(int currentPage,boolean pullToRefresh){
        subscribe(apiService.getHome(currentPage*apiService.pageSize+""),home -> {
            if(home==null){
                viewer.isEmpty();
            }
            else if(home.getGoods()==null){
                viewer.noMoreData();
            }
            else {
                viewer.HomeReturn(home);
            }
        },pullToRefresh);
    }

    public void getMember(){

    }
}
