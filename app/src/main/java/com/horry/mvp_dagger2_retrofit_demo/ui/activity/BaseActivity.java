package com.horry.mvp_dagger2_retrofit_demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.softstao.softstaolibrary.library.mvp.activity.MvpBaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Retrofit;


/**
 * Created by clevo on 2015/6/10.
 */
public abstract class BaseActivity extends MvpBaseActivity{
    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(_setContentView());
        ButterKnife.bind(this);
        onContentChanged();
        initView();
        setupActivityComponent(AppApplication.get(this).getAppComponent());
    }

    public abstract int _setContentView();
    public abstract void initView();

    protected abstract  void setupActivityComponent(AppComponent appComponent);

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    protected void onErrorViewClicked() {
        loadData(false);
    }
}
