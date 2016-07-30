package com.horry.mvp_dagger2_retrofit_demo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.softstao.softstaolibrary.library.mvp.activity.MvpBaseActivity;
import com.softstao.softstaolibrary.library.utils.LZUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Retrofit;


/**
 * Created by clevo on 2015/6/10.
 */
public abstract class BaseActivity extends MvpBaseActivity {
    @Inject
    Retrofit retrofit;
    private int yScroll=0;
    private int imageHeight ;
    protected int pageSize = 8;
    protected int offset = 0;
    protected int currentPage = 0;

    private boolean canLoad=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(AppApplication.get(this).getAppComponent());
        ButterKnife.bind(this);
        initView();
        imageHeight= LZUtils.dipToPix(this,140);
    }

    public abstract void initView();

    protected abstract  void setupActivityComponent(AppComponent appComponent);
    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }
    @Override
    public void loadData(boolean pullToRefresh) {
        showLoading(pullToRefresh);
    }

    @Override
    public void onScrollChanged(int var1, int var2, int var3, int var4) {
        if(var4-var2>0) {
            canLoad=true;
        }
        yScroll=var2;
        if (var2 <= 0) {
            titleBar.setBackgroundColor(Color.TRANSPARENT);//AGB由相关工具获得，或者美工提供
        } else if (var2 > 0 && var2 <= imageHeight) {
            float scale = (float) var2 / imageHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            titleBar.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }

    @Override
    public void onScrollTop() {

    }

    @Override
    public void onScrollBottom() {
        if(canLoad){
            currentPage++;
            loaderText.setText("正在加载...");
            canLoad=false;
            showLoader(true);
            loadData(true);
        }
    }


    private void showLoader(boolean isShow){
        if(isShow){
            loadingView.setVisibility(View.VISIBLE);
        }
        else{
            loadingView.setVisibility(View.GONE);
        }
    }

    public void noMoreData(){
        loaderText.setText("- end -");
        showLoader(true);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        currentPage=0;
        loadData(true);
        frame.postDelayed(()->  {
            ptrFrameLayout.refreshComplete();
            ptrFrameLayout.requestLayout();
        }, 1800);
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = LZUtils.dipToPix(this,imageHeight);
    }
}
