package com.horry.mvp_dagger2_retrofit_demo.ui.activity;

import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;


import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.component.DaggerMainActivityComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.module.MainActivityModule;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainViewer{

    @BindView(R.id.tv)
    TextView textView;

    @Inject
    MainActivityPresenter presenter;

    @Override
    public int _setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        presenter.showUserName();
        textView.setOnClickListener(v -> {
            setTextView("xxx");
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    public void setTextView(String username) {
        textView.setText(username);
    }


}
