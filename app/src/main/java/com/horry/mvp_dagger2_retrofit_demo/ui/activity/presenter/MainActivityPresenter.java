package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;

import com.horry.mvp_dagger2_retrofit_demo.ui.activity.interactor.BaseInteractor;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.BaseViewer;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;

import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
public class MainActivityPresenter extends BasePresenter<BaseViewer,BaseInteractor>{

    public MainActivityPresenter(BaseViewer viewer, BaseInteractor interactor) {
        super(viewer,interactor);
    }


    public void showUserName(){
        ((MainViewer)viewer).setTextView("成功");
    }


}
