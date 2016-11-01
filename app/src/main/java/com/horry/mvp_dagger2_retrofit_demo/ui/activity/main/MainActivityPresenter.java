package com.horry.mvp_dagger2_retrofit_demo.ui.activity.main;

import com.horry.mvp_dagger2_retrofit_demo.data.ModelHelperImpl;
import com.horry.mvp_dagger2_retrofit_demo.BasePresenter;

import javax.inject.Inject;

/**
 * Created by clevo on 2015/6/10.
 */
public class MainActivityPresenter extends BasePresenter<MainActivity> {


    /**
     * 构造方法
     *
     * @param viewer
     * @param helper
     */
    @Inject
    public MainActivityPresenter(MainActivity viewer, ModelHelperImpl helper) {
        super(viewer, helper);
    }


    public void showUserName(){
        viewer.setTextView("成功");
    }

    public void getCode(){
//        subscribe(apiService.getCode("18127918232"), code-> {
//            viewer.setTextView(code.getCode()+"");
//        },true);
    }

}
