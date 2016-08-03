package com.horry.mvp_dagger2_retrofit_demo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.component
        .DaggerMainActivityComponent;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.module.MainActivityModule;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.presenter.MainActivityPresenter;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home.HomeFragment;
import com.softstao.softstaolibrary.library.widget.CustomViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainViewer{

    @BindView(R.id.tv)
    TextView textView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Inject
    UserManager userManager;

    @Inject
    MainActivityPresenter presenter;

    @Override
    public int _setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initTitle("首页");
        presenter.showUserName();
        textView.setOnClickListener(v -> {
            presenter.getCode();
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new HomeFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
        viewPager.getLayoutParams().height=getScreenHeight();
        viewPager.setCurrentItem(0);
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

    @Override
    public void loadData(boolean pullToRefresh) {
        super.loadData(pullToRefresh);
    }
}
