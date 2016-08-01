package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;

import com.horry.mvp_dagger2_retrofit_demo.ui.activity.interactor.BaseInteractor;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;
import com.softstao.softstaolibrary.library.mvp.presenter.BasePresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
public class MainActivityPresenter extends BasePresenter<MainViewer> {


    /**
     * 构造方法
     *
     * @param viewer
     * @param retrofit
     */
    public MainActivityPresenter(MainViewer viewer, Retrofit retrofit) {
        super(viewer, retrofit);
    }

    public void showUserName(){
        viewer.setTextView("成功");
    }


}
