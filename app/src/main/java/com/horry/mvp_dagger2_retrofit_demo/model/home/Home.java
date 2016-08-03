package com.horry.mvp_dagger2_retrofit_demo.model.home;

import com.horry.mvp_dagger2_retrofit_demo.model.goods.Goods;
import com.softstao.softstaolibrary.library.widget.cyckeView.BasePic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public class Home implements Serializable {

    private List<BasePic> flashes;
    private List<Product> category;
    private List<Goods> goods;

    public List<BasePic> getFlashes() {
        return flashes;
    }

    public void setFlashes(List<BasePic> flashes) {
        this.flashes = flashes;
    }

    public List<Product> getCategory() {
        return category;
    }

    public void setCategory(List<Product> category) {
        this.category = category;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
