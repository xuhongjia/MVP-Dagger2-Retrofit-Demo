package com.horry.mvp_dagger2_retrofit_demo.model.goods;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Specs {
    private String id;
    private String spec;
    private String stocks;
    private String price;
    private String promote_price;
    private String goods_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(String promote_price) {
        this.promote_price = promote_price;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
