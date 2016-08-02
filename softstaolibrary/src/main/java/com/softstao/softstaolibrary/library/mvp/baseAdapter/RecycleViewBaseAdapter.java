package com.softstao.softstaolibrary.library.mvp.baseAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public abstract class RecycleViewBaseAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    public interface onClickListener{
        void onItemClick(int position);
    }
    protected List<T> mdatas;
    private onClickListener listener;
    protected int layoutId;
    public RecycleViewBaseAdapter(List<T> datas,int layoutId){
        this.mdatas=datas;
        this.layoutId=layoutId;
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, null);
        final RecycleViewHolder holder = new  RecycleViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        convert(holder,mdatas.get(position));
    }
    public abstract void convert(RecycleViewHolder holder,T t);
    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public onClickListener getListener() {
        return listener;
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }
    public int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }
}
