package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
//        apiService.getHome(currentPage*apiService.pageSize+"")
//                .map(homeResponse -> homeResponse.getData())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( home-> {
//                    if(home==null){
//                        viewer.isEmpty();
//                    }
//                    else if(home.getGoods()==null){
//                        viewer.noMoreData();
//                    }
//                    else {
//                        viewer.HomeReturn(home);
//                    }
//                },throwable -> onError(throwable,pullToRefresh),onCompleted(););
        handler.postDelayed(()->{
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
        },200);
    }

    public void getMember(){

    }
}
