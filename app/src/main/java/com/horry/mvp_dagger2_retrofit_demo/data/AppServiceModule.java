package com.horry.mvp_dagger2_retrofit_demo.data;


import com.horry.mvp_dagger2_retrofit_demo.model.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clevo on 2015/6/13.
 */
@Module
public class AppServiceModule {

    @Provides
    User provideUser() {
        User user = new User();
        user.setId("1");
        user.setName("wwww");
        return user;
    }
}
