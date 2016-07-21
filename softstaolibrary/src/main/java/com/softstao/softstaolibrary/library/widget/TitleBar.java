package com.softstao.softstaolibrary.library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.softstao.softstaolibrary.R;

import com.softstao.softstaolibrary.library.utils.LZUtils;

/**
 * Created by apple2310 on 15/4/20.
 */
public class TitleBar extends Fragment {
    private View mainView;
    private Context mContext;

    private FrameLayout leftlayout;
    private ImageView backImageView;
    private ImageView leftImageView;
    private TextView leftTextView;

    private FrameLayout centerLayout;
    private TextView centerTextView;
    private EditText searchView;

    private FrameLayout rightLayout;
    private TextView rightTextView;
    private ImageView rightImageView;

    private View divider;

    private int backgroundColor = 0xfff;
    private int fontColor = 0x000;
    private int fontSize = 14;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.mContext = getActivity();
        mainView = inflater.inflate(R.layout.title_bar, container, false);

        initView();

        return mainView;
    }

    public View getMainView() {
        return mainView;
    }

    public void setMainView(View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);

        TypedArray a = activity.obtainStyledAttributes(attrs,R.styleable.TitleBar);

        fontColor = a.getColor(R.styleable.TitleBar_titlebar_font_color, fontColor);
        backgroundColor = a.getColor(R.styleable.TitleBar_titlebar_background, backgroundColor);
        fontSize = a.getInt(R.styleable.TitleBar_titlebar_font_size,fontSize);

    }

    private void initView()
    {
        mainView.setBackgroundColor(backgroundColor);

        leftlayout = (FrameLayout) mainView.findViewById(R.id.left_fr);
        backImageView = (ImageView) leftlayout.findViewById(R.id.back_btn);
        leftImageView = (ImageView) leftlayout.findViewById(R.id.left_iv);
        leftlayout.setVisibility(View.INVISIBLE);
        leftTextView = (TextView) leftlayout.findViewById(R.id.left_text_view);


        centerLayout = (FrameLayout) mainView.findViewById(R.id.center_fr);
        centerLayout.setVisibility(View.GONE);
        centerTextView = (TextView) centerLayout.findViewById(R.id.center_tv);
        searchView = (EditText) centerLayout.findViewById(R.id.search_view);

        rightLayout = (FrameLayout) mainView.findViewById(R.id.right_fr);
        rightLayout.setVisibility(View.INVISIBLE);

        rightTextView = (TextView) rightLayout.findViewById(R.id.right_tv);
        rightImageView = (ImageView) rightLayout.findViewById(R.id.right_iv);

        divider = mainView.findViewById(R.id.divider);
    }

    public void setCenterLayout(View view)
    {
        centerLayout.removeAllViews();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.leftMargin = LZUtils.dipToPix(mContext,10);
        layoutParams.rightMargin = LZUtils.dipToPix(mContext,10);
        centerLayout.addView(view,layoutParams);
    }

    public void setBackButtonVisible()
    {
        leftlayout.setVisibility(View.VISIBLE);
        backImageView.setVisibility(View.VISIBLE);
        leftlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    public void setBackButton(int backbtn)
    {
        setBackButtonVisible();
        backImageView.setImageResource(backbtn);
    }

    public void setLeftText(String text)
    {
      setLeftTextView(text,0);
    }

    public void setLeftTextView(String text,int leftDrawble)
    {
        leftlayout.setVisibility(View.VISIBLE);
        leftTextView.setVisibility(View.VISIBLE);
        backImageView.setVisibility(View.GONE);
        leftImageView.setVisibility(View.GONE);
        leftTextView.setText(text);
        leftTextView.setTextColor(fontColor);
        if(leftDrawble != 0)
        {
            leftTextView.setCompoundDrawablePadding(LZUtils.dipToPix(mContext,5));
            leftTextView.setCompoundDrawablesWithIntrinsicBounds(leftDrawble,0,0,0);
        }
    }

    public void setRightText(String text)
    {
        rightLayout.setVisibility(View.VISIBLE);
        rightTextView.setVisibility(View.VISIBLE);
        rightImageView.setVisibility(View.GONE);
        rightTextView.setText(text);
        rightTextView.setTextColor(fontColor);
    }

    public void setRightTextEnable(boolean flag)
    {
        rightTextView.setEnabled(flag);
    }

    public void setRightTextVisible(boolean visible)
    {
        if(visible)
        {
            rightLayout.setVisibility(View.VISIBLE);
            rightTextView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.GONE);
            rightTextView.setTextColor(fontColor);
        }
        else
        {
            rightLayout.setVisibility(View.GONE);
            rightTextView.setVisibility(View.GONE);
            rightImageView.setVisibility(View.GONE);
        }

    }

    public void setLeftIcon(int iconResource)
    {
        leftlayout.setVisibility(View.VISIBLE);
        backImageView.setVisibility(View.GONE);
        leftImageView.setVisibility(View.VISIBLE);
        leftImageView.setBackgroundResource(iconResource);
    }

    public void setRightIcon(int iconResource)
    {
        rightLayout.setVisibility(View.VISIBLE);
        rightTextView.setVisibility(View.GONE);
        rightImageView.setVisibility(View.VISIBLE);
        rightImageView.setBackgroundResource(iconResource);
    }

    public void setLetfTextViewOnClick(View.OnClickListener listener)
    {
        leftTextView.setOnClickListener(listener);
    }


    public void setRightTextViewOnClick(View.OnClickListener listener)
    {
        rightTextView.setOnClickListener(listener);
    }

    public void setLeftIconOnClick(View.OnClickListener listener)
    {
        leftImageView.setOnClickListener(listener);
    }

    public void setRightIconOnClick(View.OnClickListener listener)
    {
        rightImageView.setOnClickListener(listener);
    }

    public void setTitle(String title)
    {
        centerLayout.setVisibility(View.VISIBLE);
        centerTextView.setVisibility(View.VISIBLE);
        centerTextView.setText(title);
        centerTextView.setTextColor(fontColor);
    }

    public void setFontColor(int color)
    {
        centerTextView.setTextColor(color);
        rightTextView.setTextColor(color);
        this.fontColor = color;
    }

    public void setSearchViewVisible()
    {
        centerLayout.setVisibility(View.VISIBLE);
        centerTextView.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
    }

    public String getSearchContent()
    {
        return searchView.getText().toString();
    }

    public void setTitleSize(int size)
    {
        centerTextView.setTextSize(size);
    }

    public void setBackgroundColor(int color)
    {
        mainView.setBackgroundColor(color);
    }

    public void setThemeColor(int themeColor) {
        this.fontColor = themeColor;
    }

    public void setTitileBackgroundColor(int color)
    {
        mainView.setBackgroundColor(color);
    }

    public void setVisibility(int visibility)
    {
        if(visibility == View.VISIBLE)
        {
            getView().setVisibility(View.VISIBLE);
        }
        else
        {
            getView().setVisibility(View.GONE);
        }
    }

    public String getLeftText()
    {
        return leftTextView.getText().toString();
    }

    public View getDivider() {
        return divider;
    }

    public void setDivider(View divider) {
        this.divider = divider;
    }

    public EditText getSearchView() {
        return searchView;
    }

    public void setSearchView(EditText searchView) {
        this.searchView = searchView;
    }
}
