package com.horry.mvp_dagger2_retrofit_demo.model.shop;



import com.horry.mvp_dagger2_retrofit_demo.model.goods.Goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public class ShopConfirm implements Serializable {

    /**
     * shop_id : 0
     * total_price : 22
     * shipping_fee : 0
     * goods : []
     */

    private int shop_id;
    private double total_price;
    private double shipping_fee;
    private List<Goods> goods;
    /**
     * shop_name : 全城效应
     * shop_avatar : http://yidian2015.b0.upaiyun.com/uploadfiles/avatar/2016/07/26/1672612150227774.jpg
     */

    private String shop_name;
    private String shop_avatar;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_avatar() {
        return shop_avatar;
    }

    public void setShop_avatar(String shop_avatar) {
        this.shop_avatar = shop_avatar;
    }
}
