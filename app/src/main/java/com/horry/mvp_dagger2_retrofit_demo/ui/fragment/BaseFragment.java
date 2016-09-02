package com.horry.mvp_dagger2_retrofit_demo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
import com.softstao.softstaolibrary.library.mvp.fragment.MvpBaseFragment;
import com.softstao.softstaolibrary.library.utils.LZUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by xuhon on 2016/7/31.
 */
public abstract class BaseFragment extends MvpBaseFragment {
    private int yScroll=0;
    private int imageHeight ;
    public int pageSize = 8;
    public int offset = 0;
    protected Integer currentPage = 0;
    private int red;
    private int green;
    private int blue;
    private boolean canLoad=true;
    private boolean isChange=false;

    @Inject
    UserManager userManager;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupFragmentComponent(AppApplication.get(getActivity()).getAppComponent());
        scrollerView.setOnScrollChangedListener(this);
        imageHeight= LZUtils.dipToPix(getActivity(),140);
        initView();
        loadData(false);
        red = Color.red(getResources().getColor(R.color.colorPrimary));
        green = Color.green(getResources().getColor(R.color.colorPrimary));
        blue = Color.blue(getResources().getColor(R.color.colorPrimary));
    }

    public abstract void initView();

    protected abstract  void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void loadData(boolean pullToRefresh) {
        if(!pullToRefresh){
            showLoading(pullToRefresh);
        }
    }

    @Override
    public void setButterKnife(View view) {
        ButterKnife.bind(view);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void onScrollChanged(int var1, int var2, int var3, int var4) {
        if(var4-var2>0) {
            canLoad=true;
            showLoader(false);
        }
        yScroll=var2;
        if(titleBar!=null&&isChange){
            if (var2 <= 0) {
                titleBar.setBackgroundColor(Color.TRANSPARENT);//AGB由相关工具获得，或者美工提供
            } else if (var2 > 0 && var2 <= imageHeight) {
                float scale = (float) var2 / imageHeight;
                float alpha = (255 * scale);
                // 只是layout背景透明(仿知乎滑动效果)
                //设置滑动渐变颜色
                titleBar.setBackgroundColor(Color.argb((int) alpha, red, green, blue));
                tintManager.setStatusBarTintColor(Color.argb((int) alpha, red, green, blue));
            } else {
                titleBar.setBackgroundColor(Color.argb( 255, red, green, blue));
                tintManager.setStatusBarTintColor(Color.argb(255, red, green, blue));
            }
        }
    }

    @Override
    public void onScrollTop() {

    }

    protected void onRefresh(){
        currentPage=0;
        loadData(true);
    }

    @Override
    public void onScrollBottom() {
        if(canLoad){
            currentPage++;
            loader.setVisibility(View.VISIBLE);
            loaderText.setText("正在加载...");
            canLoad=false;
            showLoader(true);
            loader.postDelayed(()->{
                loadData(true);
            },1000);
        }
    }

    private void showLoader(boolean isShow){
        if(isShow){
            loaderLayout.setVisibility(View.VISIBLE);
        }
        else{
            loaderLayout.setVisibility(View.GONE);
        }
    }

    public void noMoreData(){
        currentPage--;
        loaderText.setText("- end -");
        loader.setVisibility(View.INVISIBLE);
        showLoader(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void isEmpty(){
        showEmpty(false);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        frame.postDelayed(()->  {
            onRefresh();
        }, 1800);
    }

    @Override
    public void closePtrFrameLayout() {
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void noLogin() {

    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        if(change){
            titleBar.setBackgroundColor(Color.TRANSPARENT);
            titleBar.getDivider().setVisibility(View.GONE);
        }
        isChange = change;
    }
}
