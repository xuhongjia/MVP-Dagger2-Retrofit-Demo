package com.softstao.softstaolibrary.library.mvp.baseAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/4.
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public RecycleViewHolder(View ItemView) {
        super(ItemView);
        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }
    public RecycleViewHolder setText(int id,String text){
        ((TextView)getView(id)).setText(text);
        return this;
    }

}
