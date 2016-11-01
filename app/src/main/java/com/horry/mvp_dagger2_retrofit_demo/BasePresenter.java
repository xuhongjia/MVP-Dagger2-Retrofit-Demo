package com.horry.mvp_dagger2_retrofit_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.horry.mvp_dagger2_retrofit_demo.data.ModelHelper;
import com.horry.mvp_dagger2_retrofit_demo.data.ModelHelperImpl;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.model.Response;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.BaseFragment;
import com.softstao.softstaolibrary.library.mvp.presenter.MvpPresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *  MVP中的Presenter基类
 *  * Created by xuhon on 2016/7/29.
 * @param <V> 继承BaseViewer
 */
public abstract class BasePresenter<V extends BaseViewer> implements MvpPresenter<V> {
    /**
     * MVP中的V
     */
    protected V viewer;

    protected ModelHelperImpl helper;
    protected Integer currentPage = 0;
    /**
     * 构造方法
     * @param helper
     */
    public BasePresenter(ModelHelperImpl helper){
        this.helper =helper;
    }

    /**
     * 构造方法
     * @param viewer
     * @param helper
     */
    public BasePresenter(V viewer,ModelHelperImpl helper){
        this.viewer=viewer;
        this.helper =helper;
    }

    /**
     * 订阅对象
     */
    private Subscription subscription;

    /**
     * 注销监听者
     */
    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
    }

    /**
     *  进行订阅服务
     * @param observable 通过retrofit.create生成的observable
     * @param action1   实际处理请求的
     * @param pullToRefresh 是否在刷新
     */
    public <T> void subscribe(Observable<Response<T>> observable, Action1<T> action1, final boolean pullToRefresh) {
        if (viewer!=null) {
            viewer.showLoading(pullToRefresh);
        }
        unsubscribe();
        subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(mResponse ->{
                    int error = mResponse.getError();
                    if(error!=0){
                        int messageType = mResponse.getMsg_type();
                        if(messageType == -100){
                            error=-100;
                        }
                        switch (error){
                            case 1:
                                Observable.error(new Throwable(mResponse.getMsg()));
                                return false;
                            case -100:
                                viewer.noLogin();
                                return false;
                        }
                    }
                    return true;
                })
                .map(mResponse -> {
                    if (viewer!=null) {
                        viewer.showContent();
                        viewer.closePtrFrameLayout();
                        viewer.showLoader(false);
                    }
                    return mResponse.getData();
                })
                .retryWhen(observable1 -> observable1.flatMap(throwable->{
                    if (throwable instanceof UnknownHostException) {
                        Observable.error(throwable) ;
                    }
                    return Observable.timer(5, TimeUnit.SECONDS);
                }))
                .subscribe(t -> Observable.timer(500,TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread()).subscribe(aLong -> action1.call(t)),
                        throwable -> {this.onError(throwable,pullToRefresh);},
                        this::unsubscribe);
    }

    /**
     * 时间错误调用
     * @param e 错误信息
     * @param pullToRefresh 是否正在刷新
     */
    protected void onError(Throwable e, boolean pullToRefresh) {
        if (viewer!=null) {
            viewer.showError(e, pullToRefresh);
            viewer.closePtrFrameLayout();
        }
        unsubscribe();
    }

    public V getViewer() {
        return viewer;
    }

    public void setViewer(V viewer) {
        this.viewer = viewer;
    }
}
