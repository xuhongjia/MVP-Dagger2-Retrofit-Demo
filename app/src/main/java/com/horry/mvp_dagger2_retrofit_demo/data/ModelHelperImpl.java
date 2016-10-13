package com.horry.mvp_dagger2_retrofit_demo.data;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.global.SharePreferenceManager;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
import com.horry.mvp_dagger2_retrofit_demo.model.Response;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by xuhon on 2016/10/13.
 */
public class ModelHelperImpl extends ModelHelper {

    @Inject
    public ModelHelperImpl(SharePreferenceManager sharePreferenceManager, UserManager userManager, ApiService apiService) {
        super(sharePreferenceManager, userManager, apiService);
    }

    public Observable<Response<Home>> getHome(int currentPage){
        return apiService.getHome(currentPage*apiService.pageSize+"");
    }
}
