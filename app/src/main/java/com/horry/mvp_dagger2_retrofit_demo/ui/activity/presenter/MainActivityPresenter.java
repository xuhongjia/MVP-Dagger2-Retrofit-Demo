package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
public class MainActivityPresenter extends BasePresenter<MainActivity> {


    /**
     * 构造方法
     *
     * @param viewer
     * @param retrofit
     */
    public MainActivityPresenter(MainActivity viewer, Retrofit retrofit) {
        super(viewer, retrofit);
    }

    public void showUserName(){
        viewer.setTextView("成功");
    }

    public void getCode(){
        ApiService apiService = retrofit.create(ApiService.class);
        subscribe(apiService.getCode("13724194657"),response-> {
            viewer.setTextView(response.toString());
        },true);
    }

}
