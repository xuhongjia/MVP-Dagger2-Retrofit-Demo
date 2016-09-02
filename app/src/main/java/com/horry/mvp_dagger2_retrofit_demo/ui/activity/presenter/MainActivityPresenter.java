package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
public class MainActivityPresenter extends BasePresenter<MainActivity> {


    /**
     * 构造方法
     *
     * @param viewer
     * @param apiService
     */
    @Inject
    public MainActivityPresenter(MainActivity viewer, ApiService apiService) {
        super(viewer, apiService);
    }

    @Override
    public void loadData(Object t) {

    }

    public void showUserName(){
        viewer.setTextView("成功");
    }

    public void getCode(){
        subscribe(apiService.getCode("18127918232"), code-> {
            viewer.setTextView(code.getCode()+"");
        },true);
    }

}
