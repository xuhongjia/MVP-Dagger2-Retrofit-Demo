package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.softstao.softstaolibrary.R;

/**
 * Created by jacob on 15/6/18.
 */
public class CustomPopWindow extends PopupWindow {

    protected View maskView;
    protected LinearLayout mainView;
    protected LinearLayout contentViewLL;
    protected TextView titleView;
    private LinearLayout buttonGroup;
    protected Button cancelBtn;
    protected Button confirmBtn;
    protected Context mContext;
    protected OnPopWindowListener onPopWindowListener;
    protected View parentView;

    public CustomPopWindow(Context context, View contentView) {
        super(contentView);
        mContext = context;
        maskView = LayoutInflater.from(context).inflate(R.layout.layout_custom_popwindow, null);
        mainView = (LinearLayout) maskView.findViewById(R.id.main_view);
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        titleView = (TextView) maskView.findViewById(R.id.title_view);
        contentViewLL = (LinearLayout) maskView.findViewById(R.id.content_view);
        buttonGroup = (LinearLayout) maskView.findViewById(R.id.button_group);
        cancelBtn = (Button) buttonGroup.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.activity_translate_out));
                dismiss();
                if(onPopWindowListener != null)
                {
                    onPopWindowListener.onCancel();
                }
            }
        });

        confirmBtn = (Button) buttonGroup.findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.activity_translate_out));
                dismiss();
                if (onPopWindowListener != null)
                {
                    onPopWindowListener.onConfirm();
                }
            }
        });


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentViewLL.addView(contentView, layoutParams);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(maskView);
        maskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.activity_translate_out));
                dismiss();
            }
        });
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void setTitle(String title) {
        titleView.setVisibility(View.VISIBLE);
        titleView.setText(title);
    }

    public void show(View parentView) {
        this.parentView = parentView;
        mainView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.activity_translate_in));
        showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    public void setOnPopWindowListener(OnPopWindowListener onPopWindowListener) {
        this.onPopWindowListener = onPopWindowListener;
    }

    public interface OnPopWindowListener
    {
        public void onCancel();
        public void onConfirm();
    }
}
