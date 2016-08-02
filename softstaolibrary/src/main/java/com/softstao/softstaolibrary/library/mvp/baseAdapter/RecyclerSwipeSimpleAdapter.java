package com.softstao.softstaolibrary.library.mvp.baseAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.softstao.softstaolibrary.library.widget.ItemSlideHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public abstract class RecyclerSwipeSimpleAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> implements ItemSlideHelper.Callback {
    protected RecyclerView mRecyclerView;
    protected List<T> mdatas;
    private onClickListener listener;
    protected int layoutId;
    public RecyclerSwipeSimpleAdapter(List<T> datas, int layoutId){
        this.mdatas=datas;
        this.layoutId=layoutId;
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, null);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if(holder.itemView instanceof LinearLayout){
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if(viewGroup.getChildCount() == 2){
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    public interface onClickListener{
        void onItemClick(int position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }
    public int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }
}
