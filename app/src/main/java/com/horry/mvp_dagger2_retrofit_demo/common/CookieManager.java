package com.horry.mvp_dagger2_retrofit_demo.common;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2016/7/20.
 */
public class CookieManager implements CookieJar {
    private static CookieManager _CookieManager= null;
    private List<Cookie> cookie = new ArrayList<>();
    private CookieManager(){}

    public static CookieManager get(){
        if(_CookieManager==null){
            _CookieManager = new CookieManager();
        }
        return _CookieManager;
    }
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0 &&cookie.size()==0) {
            cookie.addAll(cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookie;
    }
}
