package com.horry.mvp_dagger2_retrofit_demo.ui.viewer;

import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

/**
 * Created by xuhon on 2016/8/3.
 */
public interface HomeViewer extends BaseViewer {
    //回调
    void HomeReturn();
    void resultMember(User user);
}
