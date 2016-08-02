package com.horry.mvp_dagger2_retrofit_demo.model;

/**
 * Created by xuhon on 2016/8/2.
 */
public class Response<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
