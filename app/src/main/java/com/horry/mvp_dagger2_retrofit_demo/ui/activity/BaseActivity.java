package com.horry.mvp_dagger2_retrofit_demo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.global.UserManager;
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

    private int yScroll=0;
    /**
     * 需要变化的高度
     */
    private int imageHeight ;
    /**
     * 当前页面
     */
    protected int currentPage = 0;
    /**
     * 颜色的红绿蓝
     */
    private int red;
    private int green;
    private int blue;
    /**
     * 是否可以上拉刷新
     */
    private boolean canLoad=true;

    /**
     * 是否变化导航条的颜色
     */
    private boolean isChange=false;
    /**
     * 用户信息
     */
    @Inject
    UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setNoScroller(true);
        super.onCreate(savedInstanceState);
        setupActivityComponent(AppApplication.get(this).getAppComponent());
        ButterKnife.bind(this);
        initView();
        red = Color.red(getResources().getColor(R.color.colorPrimary));
        green = Color.green(getResources().getColor(R.color.colorPrimary));
        blue = Color.blue(getResources().getColor(R.color.colorPrimary));
//        scrollerView.setOnScrollChangedListener(this);
        imageHeight= LZUtils.dipToPix(this,140);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * dagger2的inject
     * @param appComponent
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if(!pullToRefresh){
            showLoading(pullToRefresh);
        }
    }

    /**
     * 数据刷新
     */
    protected void onRefresh(){
        currentPage=0;
        loadData(true);
    }

    /**
     * 滑动事件
     * @param var1 new_x
     * @param var2 new_y
     * @param var3 old_x
     * @param var4 old_y
     */
    @Override
    public void onScrollChanged(int var1, int var2, int var3, int var4) {
        if(var4-var2>0) {
            canLoad=true;
//            showLoader(false);
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
            } else {
                titleBar.setBackgroundColor(Color.argb( 255, red, green, blue));
            }
        }
    }

    @Override
    public void onScrollTop() {

    }

    /**
     * 滑动到底部
     */
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

    /**
     * 显示加载更多view
     * @param isShow 是否显示
     */
    private void showLoader(boolean isShow){
        if(isShow){
            loaderLayout.setVisibility(View.VISIBLE);
        }
        else{
            loaderLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 没有更多显示没有数据
     */
    public void noMoreData(){
        currentPage--;
        loaderText.setText("- end -");
        loader.setVisibility(View.INVISIBLE);
        showLoader(true);
    }

    /**
     * 显示数据空view
     */
    public void isEmpty(){
        showEmpty(false);
    }

    /**
     * 没有登陆操作
     */
    @Override
    public void noLogin() {

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

    /**
     * 关闭头部刷新
     */
    @Override
    public void closePtrFrameLayout() {
        ptrFrameLayout.refreshComplete();
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = LZUtils.dipToPix(this,imageHeight);
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}
