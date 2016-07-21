package com.softstao.softstaolibrary.library.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.softstao.softstaolibrary.R;


/**
 * Created by jacob on 15/4/28.
 */
public class LZToast extends Toast {

    private Context mContext;
    private View mainView;
    public static LZToast toast;

    private TextView contentView;


    public static LZToast getInstance(Context context)
    {
        if(toast == null)
        {
            toast = new LZToast(context);
        }

        return  toast;
    }
    public LZToast(Context context) {
        super(context);

        mContext = context;

        initView();
    }

    private void initView()
    {
        mainView = LayoutInflater.from(mContext).inflate(R.layout.toast_view,null);
        contentView = (TextView) mainView.findViewById(R.id.content);
        setView(mainView);
    }

    public void showToast(String text)
    {
        contentView.setText(text);
        setDuration(Toast.LENGTH_SHORT);
        setGravity(Gravity.CENTER,0,200);
        show();
    }

}
