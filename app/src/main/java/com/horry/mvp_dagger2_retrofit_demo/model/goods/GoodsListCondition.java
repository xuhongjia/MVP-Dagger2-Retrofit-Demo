package com.horry.mvp_dagger2_retrofit_demo.model.goods;

/**
 * Created by Administrator on 2016/5/19.
 */
public class GoodsListCondition {
    private String category_id;
    private String brand_id;
    private String shop_id;
    private String country_id;
    private String min_price;
    private String max_price;
    private String keyword;
    private String order_field;
    private String order_sort;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrder_field() {
        return order_field;
    }

    public void setOrder_field(String order_field) {
        this.order_field = order_field;
    }

    public String getOrder_sort() {
        return order_sort;
    }

    public void setOrder_sort(String order_sort) {
        this.order_sort = order_sort;
    }
}
