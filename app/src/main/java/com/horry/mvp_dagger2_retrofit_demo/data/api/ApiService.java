package com.horry.mvp_dagger2_retrofit_demo.data.api;

import com.horry.mvp_dagger2_retrofit_demo.model.User;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;


/**
 * Created by clevo on 2015/6/10.
 */
public interface ApiService {

    @GET("/users")
    void getUsers(Callback<List<User>> callback);
}
