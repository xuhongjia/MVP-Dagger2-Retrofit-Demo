package com.softstao.softstaolibrary.library.mvp.activity;

import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.softstao.softstaolibrary.R;
import com.softstao.softstaolibrary.library.mvp.animator.DefaultAnimator;
import com.softstao.softstaolibrary.library.mvp.viewer.BaseViewer;

/**
 * MVP框架中V的基类
 * Created by xuhon on 2016/7/29.
 */
public abstract class MvpBaseActivity extends AppCompatActivity implements BaseViewer{
    /**
     * 正在加载的View
     */
    protected View loadingView;
    /**
     * 内容View
     */
    protected View contentView;
    /**
     * 出错显示的View
     */
    protected View errorView;
    /**
     * 错误View中的子View
     */
    protected TextView errorMsg;


    /**
     * 加载View
     */
    @CallSuper
    @Override
    public void onContentChanged() {
        loadingView = findViewById(R.id.loadingView);
        contentView =  findViewById(R.id.contentView);
        errorView = findViewById(R.id.errorView);
        errorMsg = (TextView) findViewById(R.id.errorMsg);
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

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onErrorViewClicked();
            }
        });
    }

    /**
     * 错误View的点击事件
     */
    protected void onErrorViewClicked() {
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
        animateContentViewIn();
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
            this.errorMsg.setText(errorMsg);
            animateErrorViewIn();
        }
    }

    /**
     * 显示错误View时的动画
     */
    protected void animateErrorViewIn() {
        DefaultAnimator.showErrorView(loadingView, contentView, errorView);
    }

}
