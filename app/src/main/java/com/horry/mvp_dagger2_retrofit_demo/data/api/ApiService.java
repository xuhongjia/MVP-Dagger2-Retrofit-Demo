package com.horry.mvp_dagger2_retrofit_demo.data.api;

import com.horry.mvp_dagger2_retrofit_demo.model.Code;
import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.model.Response;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by horry on 2016/8/3.
 */
public interface ApiService {
    /**
     * 自定义调用方法
     * @param moblie 自定义的参数
     * @return 自定义的Observable<Response<Code>>
     */
    @FormUrlEncoded
    @POST("api.php?app=passport&act=check_mobile")
    Observable<Response<Code>> getCode(@Field("mobile") String moblie);

    /**
     * 公用的POST方法
     * @param app 对应接口的app
     * @param act 对应接口的act
     * @param param 传递的参数
     * @return Observable<Response<Object>>对象
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<Response<Object>> post(@Query("app")String app,@Query("act")String act,@FieldMap Map<String,String> param);

    /**
     * 公用的GET方法
     * @param app 对应接口的app
     * @param act 对应接口的act
     * @param param 传递的参数
     * @return Observable<Response<Object>>对象
     */
    @GET("api.php")
    Observable<Response<Object>> get(@Query("app")String app, @Query("act")String act, @QueryMap Map<String,String> param);
}
