package com.softstao.softstaolibrary.library.mvp.viewer;

import android.support.annotation.UiThread;

/**
 * Created by xuhon on 2016/7/29.
 */
public interface BaseViewer {
    /**
     * 显示loadingView
     * @param pullToRefresh
     */
    @UiThread
    void showLoading(boolean pullToRefresh);

    /**
     * 显示contentView
     */
    @UiThread
    void showContent();

    /**
     * 显示错误信息
     * @param e
     * @param pullToRefresh
     */
    @UiThread
    void showError(Throwable e, boolean pullToRefresh);

    /**
     * 加载数据
     * @param pullToRefresh
     */
    @UiThread
    void loadData(boolean pullToRefresh);

    /**
     * 没有登陆调用
     */
    @UiThread
    void noLogin();

    /**
     * 显示空View
     * @param pullToRefresh
     */
    @UiThread
    void showEmpty(boolean pullToRefresh);

    /**
     * 关闭头部刷新
     */
    void closePtrFrameLayout();

    void showLoader(boolean isShow);
}
