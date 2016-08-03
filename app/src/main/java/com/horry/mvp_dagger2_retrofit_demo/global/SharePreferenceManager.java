package com.horry.mvp_dagger2_retrofit_demo.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.ActivityScope;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zhanghongbo on 16-1-13.
 */
public class SharePreferenceManager {
    private SharedPreferences mPref;
    private Gson gson;
    private String orderId;
    private boolean isSuccess=false;
    public static final String WX_APPID = "wx1ba1fab6a763f7de";
    public static final String WX_APPKEY ="780415caa50d63bdd8c95d04031dba7a";
    public static final String WX_PARTNERID ="1370714902";
    public static final String ALIPAY_PARTNER = "2088421397085005";
    public static final String ALIPAY_SELLER = "yunshequ_2016@163.com";
    public static final String ALIPAY_PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMiI6paOqwm1oXwgDJYGm7+VKJyPKKxDAOHbK5z+zd9OMGKB1zCLNVEL2fUUdb2HoCvZYXg8zAOSX0ESssq1JAiYPWRmDrxzz+jsulGV10JirR4mJyszbaDO4benOo5njd/TFN0Hl8/QQWwyPI6/c9gnAYFlG4r+5qSV3Io+ZnQzAgMBAAECgYEAoAmUnd7x5OfNHlA9eyV++qBBLuCZJxC6DYVqAvretc2ik5rnb1hJjoqUwSA47q7kRtpPDTQH5PnVhwszIM77F28HsXeDog5PaZjnVEcjAnDAU66WfaXZHELiKue6WHAs/8C+d+yheCElnzzepmLQufpVzCg71/IsjKxrVt+dqYECQQDplE2fvb+5P2MXKVKUeJEecsOJLvvnQdWBgbVr8EMlD/Ty1tLILU85c9LjylDKOzDhyzBxoLOtx3ycQwik7G2hAkEA28ieLKN21LvjzNlw4csIX3VBCf34FZAJOrls22/iU18SXgOiQCc3esSNOVnSRLb6/jikxtczxthsn2QjcPxJUwJAMsGQMJMJ81Yr0R+gmzSrzCMCDHBSOslafL7TznWyEX6rKo211dvKqGBbxjXzYk6Ea7w8daSHBs8fa9FBMp1RoQJAA6U1MmpXqzmekqQ8ohIbBnJMbN0Ag2MLNCUlMBKP/3ABPvvycfv0iJ2/9eaOfUNHsHXCDeY93ptyWcZwbAxTLwJAedj8sR5uoZISBXcxwXJUsbKEnfo5lgm2hAnlpkaRPt2Mo/q1azuYFzmc5RNjZ/uv1qeMkjC0N7ZqL67hoWNnkQ==";

    public SharePreferenceManager(AppApplication context,Gson gson) {
        mPref = context.getSharedPreferences(context.getApplicationInfo().loadLabel(context.getPackageManager()).toString(), Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public void serUser(User user){
        if(user==null){
            mPref.edit().putString("user", null).commit();
            return;
        }
        mPref.edit().putString("user", gson.toJson(user)).commit();
    }

    public User getUser(){
        String userStr = mPref.getString("user", null);
        if(userStr==null||userStr.equals("")) {
            return null;
        }
        return gson.fromJson(userStr, User.class);
    }

    public String getSign() {
        return mPref.getString("sign",null);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
