package com.horry.mvp_dagger2_retrofit_demo.ui.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.horry.mvp_dagger2_retrofit_demo.AppApplication;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.model.goods.Goods;
import com.softstao.softstaolibrary.library.mvp.baseAdapter.RecycleViewBaseAdapter;
import com.softstao.softstaolibrary.library.mvp.baseAdapter.RecycleViewHolder;
import com.softstao.softstaolibrary.library.utils.LZUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class GoodsAdapter extends RecycleViewBaseAdapter<Goods> {

    public GoodsAdapter(List<Goods> datas) {
        super(datas, R.layout.goods_item);
    }

    @Override
    public void convert(RecycleViewHolder holder, Goods goods) {
        TextView tv = holder.getView(R.id.origin_price);
        tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        tv.getPaint().setAntiAlias(true);// 抗锯齿
        tv.setText("￥"+ LZUtils.priceFormat(Double.valueOf(goods.getMarket_price()!=null?goods.getMarket_price():"0")));
        holder.setText(R.id.goods_name,goods.getName()!=null?goods.getName():"")
                .setText(R.id.price, "￥"+ LZUtils.priceFormat(Double.valueOf(goods.getPrice()!=null?goods.getPrice():"0")));
        int width=getScreenWidth(holder.itemView.getContext())/2-LZUtils.dipToPix(holder.itemView.getContext(),10);
//        Glide.with(holder.itemView.getContext())
//                .load(goods.getDefault_pic()).diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView)holder.getView(R.id.img));
//        ImageLoader.getInstance().displayImage(goods.getDefault_pic(),(ImageView)holder.getView(R.id.img));
//        ((ImageView)holder.getView(R.id.img)).setImageDrawable(holder.itemView.getResources().getDrawable(Integer.parseInt(goods.getDefault_pic())));
    }
}
