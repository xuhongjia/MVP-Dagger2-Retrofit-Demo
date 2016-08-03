package com.horry.mvp_dagger2_retrofit_demo.global;

import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by xuhon on 2016/5/16.
 */

//用户管理
public class UserManager {
    private User user;
    private SharePreferenceManager sharePreferenceManager;

    public UserManager(SharePreferenceManager sharePreferenceManager){
        this.sharePreferenceManager=sharePreferenceManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        sharePreferenceManager.serUser(user);
    }
}
