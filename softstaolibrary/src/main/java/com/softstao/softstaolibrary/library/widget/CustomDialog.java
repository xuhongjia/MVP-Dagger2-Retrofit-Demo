package com.softstao.softstaolibrary.library.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.softstao.softstaolibrary.R;


/**
 * Created by jacob on 15/5/11.
 */
public class CustomDialog extends Dialog {

    protected Builder builder;
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);

    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public static class Builder
    {
        public static Context mContext;
        protected TextView titleView;
        protected TextView contentView;
        protected ListView listView;
        protected LinearLayout contentPanel;
        protected Button cancelButton;
        protected Button confirmButton;
        protected CustomDialog customDialog;
        protected LinearLayout bottomGroup;
        protected OnDialogConfirmListener onDialogConfirmListener;
        protected int selectecIndex;
        protected BaseAdapter listAapater;
        protected CustomDialog dialog;

        public CustomDialog build(Context context)
        {
            mContext = context;
            customDialog = new CustomDialog(mContext, R.style.MyDialog);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mainView = inflater.inflate(R.layout.custom_dialog,null);

            Rect displayRectangle = new Rect();
            Window window = ((Activity)mContext).getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

            mainView.setMinimumWidth((int) (displayRectangle.width() * 0.8f));
            initView(mainView);
            customDialog.setContentView(mainView);

            return customDialog;
        }

        public CustomDialog getDialog() {
            return dialog;
        }

        public void setDialog(CustomDialog dialog) {
            this.dialog = dialog;
        }

        private void initView(View view)
        {
            titleView = (TextView) view.findViewById(R.id.title);
            contentView = (TextView) view.findViewById(R.id.message);
            contentPanel = (LinearLayout) view.findViewById(R.id.content);
            listView = (ListView) view.findViewById(R.id.list_view);

            bottomGroup = (LinearLayout) view.findViewById(R.id.dialog_btn_layout);
            cancelButton = (Button) view.findViewById(R.id.negativeButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customDialog.dismiss();
                }
            });
            confirmButton = (Button) view .findViewById(R.id.positiveButton);
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onDialogConfirmListener != null) {
                        onDialogConfirmListener.onConfirm();
                    }
                    customDialog.dismiss();
                }
            });
        }

        public void setBottomlayout(int layoutId)
        {
            bottomGroup.removeAllViews();
            View.inflate(mContext, layoutId, bottomGroup);
        }

        public void setBottomlayout(View view)
        {
            bottomGroup.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            bottomGroup.addView(view,layoutParams);
        }
        public void setContentLayout(int layoutId)
        {
            View.inflate(mContext,layoutId,contentPanel);
        }


        public void setAdapter(BaseAdapter adapter)
        {
            this.listAapater = adapter;
            contentView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adapter);
            View item = adapter.getView(0, null, listView);
            item.measure(0, 0);
            if (adapter.getCount() > 1) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, item.getMeasuredHeight() * 3);
                listView.setLayoutParams(params);
            }
            else
            {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,item.getMeasuredHeight());
                listView.setLayoutParams(params);
            }

        }


        public void setTitle(String title)
        {
            titleView.setText(title);
        }

        public void setContent(String content)
        {
            contentView.setText(content);
        }

        public interface  OnDialogConfirmListener
        {
            void onConfirm();
        }

        public void setOnDialogConfirmListener(OnDialogConfirmListener onDialogConfirmListener) {
            this.onDialogConfirmListener = onDialogConfirmListener;
        }
    }



}
