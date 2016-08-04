package com.horry.mvp_dagger2_retrofit_demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.RadioButton;

import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.MainViewer;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.BaseFragment;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home.HomeFragment;
import com.softstao.softstaolibrary.library.widget.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainViewer {

    @BindView(R.id.view_pager)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.home_radio)
    RadioButton homeRadio;
    @BindView(R.id.product_radio)
    RadioButton productRadio;
    @BindView(R.id.found_radio)
    RadioButton foundRadio;
    @BindView(R.id.me_radio)
    RadioButton meRadio;
    private RadioButton[] radioButtons = new RadioButton[4];
    private HomeFragment homeFragment;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    int currentIndex = 0;
    int selectedIndex;

    @Override
    public int _setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        hideTitle();
        radioButtons[0] = homeRadio;
        radioButtons[1] = productRadio;
        radioButtons[2] = foundRadio;
        radioButtons[3] = meRadio;
        initListener();
        homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
//        viewPager.getLayoutParams().height = getScreenHeight();
        viewPager.setCurrentItem(0);
        radioButtons[currentIndex].setSelected(true);
    }


    private void initListener(){
        MyListener myButtonListener = new MyListener();
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setOnClickListener(myButtonListener);
        }
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_radio:
                    selectedIndex = 0;
                    break;
                case R.id.product_radio:
                    selectedIndex = 1;
                    break;
                case R.id.found_radio:
                    selectedIndex = 2;
                    break;
                case R.id.me_radio:
                    selectedIndex = 3;
                    break;
            }
            if (selectedIndex != currentIndex) {
                viewPager.setCurrentItem(selectedIndex);
                radioButtons[currentIndex].setSelected(false);
                radioButtons[selectedIndex].setSelected(true);
                currentIndex = selectedIndex;
            }
        }
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setTextView(String username) {
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        super.loadData(pullToRefresh);
    }

}
