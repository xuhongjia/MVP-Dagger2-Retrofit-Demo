package com.softstao.softstaolibrary.library.mvp.baseAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用ListView的BaseAdapter
 * @author Myy
 * @param <T>
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {
    protected int layoutId;
//    protected Context context;
    protected List<T> mdatas;
    public CommonBaseAdapter(List<T> datas,int layoutId) {
//        this.context=context;
        this.mdatas=datas;
        this.layoutId=layoutId;
    }
    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public T getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO 自动生成的方法存根
        ViewHolder holder= ViewHolder.get(parent.getContext(), convertView, layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }
    public abstract void convert(ViewHolder holder,T t);

    public List<T> getMdatas() {
        return mdatas;
    }

    public void setMdatas(List<T> mdatas) {
        this.mdatas = mdatas;
    }
}
