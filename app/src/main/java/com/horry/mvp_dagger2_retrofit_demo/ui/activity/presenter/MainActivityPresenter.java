package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;


import com.horry.mvp_dagger2_retrofit_demo.ui.activity.MainActivity;
import com.horry.mvp_dagger2_retrofit_demo.model.User;

import retrofit2.Retrofit;

/**
 * Created by clevo on 2015/6/10.
 */
public class MainActivityPresenter extends BasePresenter{

    private MainActivity mainActivity;
    private User user;

    public MainActivityPresenter(MainActivity mainActivity, User user) {
        super();
        this.mainActivity = mainActivity;
        this.user = user;
    }


    public void showUserName(){
        mainActivity.setTextView(user.getName());
    }


}
