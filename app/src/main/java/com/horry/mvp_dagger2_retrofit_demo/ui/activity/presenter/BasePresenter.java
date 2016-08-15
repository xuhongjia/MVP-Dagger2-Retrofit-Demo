package com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.model.Response;
import com.softstao.softstaolibrary.library.mvp.presenter.MvpPresenter;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

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
public class BasePresenter<V extends BaseViewer> implements MvpPresenter<V> {
    /**
     * MVP中的V
     */
    protected V viewer;

    protected ApiService apiService;

    /**
     * 构造方法
     * @param viewer
     */
    @Inject
    public BasePresenter(V viewer,ApiService apiService){
        this.viewer=viewer;
        this.apiService =apiService;
    }

    /**
     * 订阅对象
     */
//    private Subscription subscription;
    private Subscription subscription;

    protected Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==100){
                String errorMsg = (String) msg.obj;
                try {
                    throw new Throwable(errorMsg);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            else if(msg.what==1){
                viewer.noLogin();
            }
        }
    };
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
    public <M> void subscribe(Observable<Response<M>> observable, Action1<M> action1, final boolean pullToRefresh) {
        if (viewer!=null) {
            viewer.showLoading(pullToRefresh);
        }
        unsubscribe();
        subscription = observable
                .map(mResponse ->{
                    int error = mResponse.getError();
                    if(error!=0){
                        int messageType = mResponse.getMsg_type();
                        if(messageType == -100){
                            error=-100;
                        }
                        handler.sendMessage(handler.obtainMessage(error,mResponse.getMsg()));
                    }
                    return mResponse.getData();
                })
                .subscribeOn(Schedulers.io())
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
            viewer.closePtrFrameLayout();
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
            viewer.closePtrFrameLayout();
        }
        unsubscribe();
    }

    private class ResponseFunc<T> implements Func1<Response<T>, T> {

        @Override
        public T call(Response<T> tResponse) {
            return null;
        }
    }
}
