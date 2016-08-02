package com.horry.mvp_dagger2_retrofit_demo.ui.activity;


import android.support.v7.widget.RecyclerView;

import com.horry.mvp_dagger2_retrofit_demo.R;
import com.softstao.softstaolibrary.library.mvp.baseAdapter.RecycleViewBaseAdapter;
import com.softstao.softstaolibrary.library.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class BaseListActivity<T> extends BaseActivity  {
    protected List<T> datas = new ArrayList<T>();
    protected RecycleViewBaseAdapter<T> adapter;
    @BindView(R.id.list)
    protected RecyclerView mRecycleView;

    @Override
    public void initView() {
        mRecycleView.setLayoutManager(new FullyLinearLayoutManager(this));
        scrollerView.setOnScrollChangedListener(this);
    }

    @Override
    public int _setContentView() {
        return R.layout.base_list;
    }

    @Override
    protected void onViewClicked() {
        showLoading(false);
        onRefresh();
        super.onViewClicked();
    }

    @Override
    public void onRefresh() {
        datas.clear();
        super.onRefresh();
    }
}
