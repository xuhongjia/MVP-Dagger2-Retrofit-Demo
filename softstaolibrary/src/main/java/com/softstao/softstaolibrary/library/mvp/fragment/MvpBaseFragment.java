package com.softstao.softstaolibrary.library.mvp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.softstao.softstaolibrary.R;
import com.softstao.softstaolibrary.library.mvp.activity.MvpBaseActivity;
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


public abstract class MvpBaseFragment extends Fragment implements BaseViewer,View.OnClickListener ,PtrHandler,CustomScrollerView.OnScrollChangedListener{


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
    public PtrFrameLayout ptrFrameLayout;
    /**
     * 数据为空的view
     */
    protected EmptyLayout emptyLayout;
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

    protected Context mContext;
    /**
     * 判断titleBar是否有rule
     */
    private boolean hasRule=false;

    public MvpBaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        tintManager = ((MvpBaseActivity)mContext).getTintManager();
        View view = inflater.inflate(R.layout.base_view, container, false);
        onContentChanged(view);
        addContentView(_setContentView(),inflater);
        container.requestLayout();
        setButterKnife(view);
        return view;
    }

    /**
     * 设置头部显示刷新可用
     */
    protected void setPtrFrameLayoutEnable(){
        ptrFrameLayout.setPtrHandler(this);
        ptrFrameLayout.disableWhenHorizontalMove(true);
        if(hasRule){
            StoreHouseHeader header = new StoreHouseHeader(mContext);
            header.setPadding(0, 55, 0, 55);
            header.initWithString(getString(R.string.app_name));
            header.setTextColor(getResources().getColor(R.color.colorPrimary));
            header.setScale(1.7f);
            header.onUIReset(ptrFrameLayout);
            ptrFrameLayout.setHeaderView(header);
            ptrFrameLayout.addPtrUIHandler(header);
        }
        else{
            MaterialHeader header = new MaterialHeader(mContext);
            header.setPadding(0, 120, 0, 55);
            int[] colors = getResources().getIntArray(R.array.colors);
            header.setColorSchemeColors(colors);
            header.onUIReset(ptrFrameLayout);
            ptrFrameLayout.setHeaderView(header);
            ptrFrameLayout.addPtrUIHandler(header);
        }
    }
    /**
     * 设置ptrFrameLayout的刷新头
     */
    protected void setHeaderView(View header){
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler((PtrUIHandler) header);
    }

    /**
     * 初始化标题栏
     * @param title 标题
     */
    public void initTitle(String title) {
        titleBar = (TitleBar) getChildFragmentManager().findFragmentById(R.id.title);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        titleBar.setLeftIcon(R.mipmap.black_back);
        titleBar.setFontColor(getResources().getColor(R.color.white));
        titleBar.setBackButtonVisible();
        titleBar.setTitle(title);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) titleBar.getMainView().getLayoutParams();
        lp.setMargins(0,tintManager.getConfig().getStatusBarHeight(),0,0);
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
     * 设置butterknife注解
     * @param view
     */
    public abstract void setButterKnife(View view);

    /**
     * 设置contentView内容layout_id
     * @return
     */
    public abstract int _setContentView();

    /**
     * 将视图加进contentView内
     * @param res_id layout_id
     * @param inflater
     */
    private void addContentView(int res_id,LayoutInflater inflater){
        if(contentView!=null){
            inflater.inflate(res_id,contentView,true);
        }
    }

    /**
     * 加载View
     */
    @CallSuper
    public void onContentChanged(View view) {
        content = view.findViewById(R.id.content);
        loadingView = view.findViewById(R.id.loading_view);
        contentView = (LinearLayout) view.findViewById(R.id.content_view);
        errorView = (ErrorLayout) view.findViewById(R.id.error_layout);
        scrollerView = (CustomScrollerView) view.findViewById(R.id.scrollView);
        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        loaderLayout = view.findViewById(R.id.loader_view);
        loader = view.findViewById(R.id.loader);
        loaderText = (TextView) view.findViewById(R.id.loader_text);
        emptyLayout = (EmptyLayout) view.findViewById(R.id.empty_layout);
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
     * 错误View的点击事件
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取屏幕宽度
     * @return 屏幕宽度
     */
    public int getScreenWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        return width;
    }

    /**
     * 获取屏幕高度
     * @return 屏幕高度
     */
    public int getScreenHeight(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;

        return height;
    }
}
