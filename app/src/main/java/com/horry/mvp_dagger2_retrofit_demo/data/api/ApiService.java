package com.horry.mvp_dagger2_retrofit_demo.data.api;

import com.horry.mvp_dagger2_retrofit_demo.model.Code;
import com.horry.mvp_dagger2_retrofit_demo.model.Response;
import com.horry.mvp_dagger2_retrofit_demo.model.User;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by clevo on 2015/6/10.
 */
public interface ApiService {

    @GET("/users")
    void getUsers(Callback<List<User>> callback);

    @FormUrlEncoded
    @POST("api.php?app=passport&act=check_mobile")
    Observable<Object> getCode(@Field("mobile") String moblie);
}
