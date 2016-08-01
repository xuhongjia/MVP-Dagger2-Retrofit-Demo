package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;

import com.horry.mvp_dagger2_retrofit_demo.ui.activity.interactor.BaseInteractor;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BasePresenter<V,I extends BaseInteractor> {

    protected V viewer;
    protected I interactor;

    public BasePresenter(V viewer,I interactor){
        this.viewer=viewer;
        this.interactor=interactor;
    }
}
