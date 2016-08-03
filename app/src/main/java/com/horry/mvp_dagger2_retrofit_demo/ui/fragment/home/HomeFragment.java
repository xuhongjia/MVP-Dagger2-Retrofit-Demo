package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.HomeViewer;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeViewer {

    @BindView(R.id.tv)
    TextView tv;
    @Inject
    HomeFragmentPresenter presenter;
    @Override
    public int _setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        initTitle("首页");
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        super.loadData(pullToRefresh);
    }

    @Override
    public void noLogin() {

    }

    @Override
    public void HomeReturn(Home home) {

    }

    @Override
    public void resultMember(User user) {

    }
}
