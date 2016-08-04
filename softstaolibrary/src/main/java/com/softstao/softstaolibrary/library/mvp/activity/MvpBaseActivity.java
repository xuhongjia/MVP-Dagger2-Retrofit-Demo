package com.softstao.softstaolibrary.library.mvp.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.softstao.softstaolibrary.R;
import com.softstao.softstaolibrary.library.global.AppManager;
import com.softstao.softstaolibrary.library.mvp.animator.DefaultAnimator;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;
import com.softstao.softstaolibrary.library.widget.CustomScrollerView;
import com.softstao.softstaolibrary.library.widget.EmptyLayout;
import com.softstao.softstaolibrary.library.widget.ErrorLayout;
import com.softstao.softstaolibrary.library.widget.TitleBar;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * MVP框架中V的基类
 * Created by xuhon on 2016/7/29.
 */
public abstract class MvpBaseActivity extends AppCompatActivity implements BaseViewer,PtrHandler,CustomScrollerView.OnScrollChangedListener,View.OnClickListener {
    /**
     * 主要内容
     */
    protected View content;
    /**
     * 正在加载的View
     */
    protected View loadingView;
    /**
     * 内容View
     */
    protected LinearLayout contentView;
    /**
     * 出错显示的View
     */
    protected ErrorLayout errorView;

    /**
     * 滑动scrollview
     */
    protected CustomScrollerView scrollerView;

    /**
     * 下拉刷新view
     */
    protected PtrFrameLayout ptrFrameLayout;

    /**
     * 数据为空的view
     */
    protected EmptyLayout emptyLayout;
    /**
     * 是否需要scroller
     */
    protected boolean noScroller=false;
    /**
     * 底部加载更多View
     */
    protected View loaderLayout;
    protected View loader;
    protected TextView loaderText;
    /**
     * 导航条
     */
    protected TitleBar titleBar;
    /**
     * 可修改的系统状态栏
     */
    protected SystemBarTintManager tintManager ;
    /**
     * 判断titleBar是否有rule
     */
    private boolean hasRule=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(noScroller){
            setContentView(R.layout.normal_base_view);
        }
        else{
            setContentView(R.layout.base_view);
        }
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));
        tintManager.setNavigationBarTintColor(getResources().getColor(R.color.colorPrimary));
        onContentChanged();
        addContentView(_setContentView());
        AppManager.getInstance().add(this);
    }

    /**
     * 设置头部显示刷新可用
     */
    protected void setPtrFrameLayoutEnable(){
        ptrFrameLayout.setPtrHandler(this);
        ptrFrameLayout.disableWhenHorizontalMove(true);
        if(hasRule){
            StoreHouseHeader header = new StoreHouseHeader(this);
            header.setPadding(0, 55, 0, 55);
            header.initWithString(getString(R.string.app_name));
            header.setTextColor(getResources().getColor(R.color.colorPrimary));
            header.setScale(1.7f);
            header.onUIReset(ptrFrameLayout);
            ptrFrameLayout.setHeaderView(header);
            ptrFrameLayout.addPtrUIHandler(header);
        }
        else{
            MaterialHeader header = new MaterialHeader(this);
            header.setPadding(0, 120, 0, 55);
            int[] colors = getResources().getIntArray(R.array.colors);
            header.setColorSchemeColors(colors);
            header.onUIReset(ptrFrameLayout);
            ptrFrameLayout.setHeaderView(header);
            ptrFrameLayout.addPtrUIHandler(header);
        }
    }
    /**
     * 必须在setPtrFrameLayoutEnable之前调用因为需要更改下拉刷新View
     * @param rule 要修改的rule
     * @param id 对应的View的id
     */
    public void changeContentRule(int rule,int id){
        hasRule=true;
        ((RelativeLayout.LayoutParams)content.getLayoutParams()).addRule(rule,id);
    }
    /**
     * 设置ptrFrameLayout的刷新头
     */
    protected void setHeaderView(View header){
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler((PtrUIHandler) header);
    }
    /**
     * 将视图加进contentView内
     * @param res_id layout_id
     */
    private void addContentView(int res_id){
        if(contentView!=null){
            getLayoutInflater().inflate(res_id,contentView,true);
        }
    }
    /**
     * 初始化标题栏
     * @param title 标题
     */
    public void initTitle(String title) {
        titleBar = (TitleBar) getSupportFragmentManager().findFragmentById(R.id.title);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        titleBar.setLeftIcon(R.mipmap.black_back);
        titleBar.setFontColor(getResources().getColor(R.color.white));
        titleBar.setBackButtonVisible();
        titleBar.setTitle(title);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) titleBar.getMainView().getLayoutParams();
        lp.setMargins(0,tintManager.getConfig().getStatusBarHeight(),0,0);
    }

    public void hideTitle(){
        findViewById(R.id.title).setVisibility(View.GONE);
    }

    public abstract int _setContentView();
    /**
     * 加载View
     */
    @CallSuper
    @Override
    public void onContentChanged() {
        content = findViewById(R.id.content);
        loadingView = findViewById(R.id.loading_view);
        contentView = (LinearLayout) findViewById(R.id.content_view);
        if(!noScroller){
            scrollerView = (CustomScrollerView) findViewById(R.id.scrollView);
            ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_frame);
        }
        errorView = (ErrorLayout) findViewById(R.id.error_layout);
        loaderLayout = findViewById(R.id.loader_view);
        loader = findViewById(R.id.loader);
        loaderText = (TextView) findViewById(R.id.loader_text);
        emptyLayout = (EmptyLayout) findViewById(R.id.empty_layout);
        if (loadingView == null) {
            throw new NullPointerException(
                    "Loading view is null! Have you specified a loading view in your layout xml file?"
                            + " You have to give your loading View the id R.id.loadingView");
        }

        if (contentView == null) {
            throw new NullPointerException(
                    "Content view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your content View the id R.id.contentView");
        }

        if (errorView == null) {
            throw new NullPointerException(
                    "Error view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your error View the id R.id.contentView");
        }

        errorView.setOnClickListener(this);
        emptyLayout.setOnClickListener(this);
    }
    /**
     * 出错的View的点击
     * @param v
     */
    @Override
    public void onClick(View v) {
        onViewClicked();
    }
    /**
     * 错误和空View的点击事件
     */
    protected void onViewClicked() {
        loadData(false);
    }

    /**
     * 显示加载LoadingView
     * @param pullToRefresh
     */
    @Override
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            animateLoadingViewIn();
        }
    }

    /**
     * 显示LoadingView时的动画
     */
    protected void animateLoadingViewIn() {
        DefaultAnimator.showLoading(loadingView, contentView, errorView);
    }

    /**
     * 显示内容View
     */
    @Override
    public void showContent() {
        if(contentView.getVisibility()!=View.VISIBLE){
            animateContentViewIn();
        }
    }

    /**
     * 显示内容View时的动画
     */
    protected void animateContentViewIn() {
        DefaultAnimator.showContent(loadingView, contentView, errorView);
    }

    /**
     * 获取错误信息
     * @param e 异常对象
     * @param pullToRefresh 是否正在刷新
     * @return 错误信息字符串
     */
    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    /**
     * 以Toast方式显示错误信息
     * @param msg 错误信息
     */
    protected void showLightError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示错误信息的处理，是否显示错误View
     * @param e 异常对象
     * @param pullToRefresh 是否正在刷新
     */
    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        String errorMsg = getErrorMessage(e, pullToRefresh);

        if (pullToRefresh) {
            showLightError(errorMsg);
        } else {
            this.errorView.setErrorMsg(errorMsg);
            animateErrorViewIn();
        }
    }

    /**
     * 显示错误View时的动画
     */
    protected void animateErrorViewIn() {
        DefaultAnimator.showErrorView(loadingView, contentView, errorView);
    }

    /**
     * 显示空View时的动画
     */
    @Override
    public void showEmpty(boolean pullToRefresh) {
        if(!pullToRefresh){
            DefaultAnimator.showEmptyView(loadingView,contentView,emptyLayout);
        }
    }
    /**
     * 显示错误View时的动画
     */
    protected void animateEmptyViewIn() {
        DefaultAnimator.showEmptyView(loadingView, contentView,emptyLayout);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }
    /**
     * 获取屏幕宽度
     * @return 屏幕宽度
     */
    public int getScreenWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        return width;
    }
    /**
     * 获取屏幕高度
     * @return 屏幕高度
     */
    public int getScreenHeight(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;

        return height;
    }

    public SystemBarTintManager getTintManager() {
        return tintManager;
    }

    public void setTintManager(SystemBarTintManager tintManager) {
        this.tintManager = tintManager;
    }

    /**
     * 点击空白地方隐藏软键盘
     * @param event 点击事件
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }

    public boolean isNoScroller() {
        return noScroller;
    }

    public void setNoScroller(boolean noScroller) {
        this.noScroller = noScroller;
    }
}
