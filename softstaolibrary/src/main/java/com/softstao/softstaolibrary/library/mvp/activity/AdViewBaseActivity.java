package com.softstao.softstaolibrary.library.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.softstao.softstaolibrary.R;

/**
 * Created by xuhon on 2016/7/30.
 */
public abstract class AdViewBaseActivity extends MvpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public abstract int setSecondView();

    public void onSecondContentView(int res_id){

    }
    @Override
    public int _setContentView() {
        return R.layout.activity_base_ad_view;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }


}
