package com.softstao.softstaolibrary.library.mvp.presenter;

import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 *  MVP中的Presenter基类
 *  * Created by xuhon on 2016/7/29.
 * @param <V> 继承BaseViewer
 */
public class BasePresenter<V extends BaseViewer> implements MvpPresenter<V> {
    /**
     * MVP中的V
     */
    protected V viewer;

    protected Retrofit retrofit;
    /**
     * 构造方法
     * @param viewer
     */
    public BasePresenter(V viewer,Retrofit retrofit){
        this.viewer=viewer;
        this.retrofit =retrofit;
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
     * @param <M> 获取的值
     */
    public <M> void subscribe(Observable<M> observable, Action1<M> action1, final boolean pullToRefresh) {

        if (viewer!=null) {
            viewer.showLoading(pullToRefresh);
        }
        unsubscribe();
        subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1,
                        throwable -> {this.onError(throwable,pullToRefresh);},
                        this::onCompleted);
    }

    /**
     * 事件结束时调用
     */
    protected void onCompleted() {
        if (viewer!=null) {
            viewer.showContent();
        }
        unsubscribe();
    }

    /**
     * 时间错误调用
     * @param e 错误信息
     * @param pullToRefresh 是否正在刷新
     */
    protected void onError(Throwable e, boolean pullToRefresh) {
        if (viewer!=null) {
            viewer.showError(e, pullToRefresh);
        }
        unsubscribe();
    }

}
