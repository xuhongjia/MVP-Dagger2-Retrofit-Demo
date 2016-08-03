package com.horry.mvp_dagger2_retrofit_demo.model.goods;

import com.horry.mvp_dagger2_retrofit_demo.model.shop.Shop;
import com.softstao.softstaolibrary.library.widget.cyckeView.BasePic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Goods implements Serializable {

    /**
     * id : 4
     * name : 爽肤水
     * huohao : ghgh
     * default_pic : http://www.yanglanzi8.com//uploadfiles/goods4.png
     * brief : null
     * keywords : null
     * content : fsdfsdf
     * weight : 4
     * size : 50
     * price : 20.00
     * cost_price : 11.00
     * promote_price : 0.00
     * promote_begin_time : 0
     * promote_end_time : 0
     * integral : 0
     * give_integral : 0
     * category_id : 1
     * shop_id : 0
     * brand_id : 0
     * country_id : 1
     * sales : 6
     * clicks : 1
     * stocks : 0
     * stock_alert_number : 1
     * comments : 0
     * ext_property : 0
     * shipping_fee : 0
     * free_shipping : 0
     * commission_template_id : null
     * commissions : 0
     * add_time : 0
     * edit_time : 0
     * sort : 999
     * status : 1
     * seller_note : null
     * in_shop : 0
     * sale_method : 1
     */

    private String id;
    private String name;
    private String huohao;
    private String default_pic;
    private String brief;
    private String keywords;
    private String content;
    private String weight;
    private String size;
    private String price="";
    private String cost_price;
    private String promote_price;
    private String promote_begin_time;
    private String promote_end_time;
    private String integral;
    private String give_integral;
    private String category_id;
    private String shop_id;
    private String brand_id;
    private String country_id;
    private String sales;
    private String clicks;
    private String stocks;
    private String stock_alert_number;
    private String comments;
    private String ext_property;
    private String shipping_fee;
    private String free_shipping;
    private String commission_template_id;
    private String commissions;
    private String add_time;
    private String edit_time;
    private String sort;
    private String status;
    private String seller_note;
    private String in_shop;
    private String sale_method;
    private String spec;
    private String quantity;
    private String spec_price;
    private String spec_promote_price;
    private String market_price;
    /**
     * id : 1
     * name : 澳洲
     * flag_pic : http://yidian2015.b0.upaiyun.com/uploadfiles/avatar/2016/05/17/1651710391815344.png
     */

    /**
     * pics : [{"pic":"http://www.yanglanzi8.com//uploadfiles/goods1.png","memo":null},{"pic":"http://www.yanglanzi8.com//uploadfiles/goods2.png","memo":null},{"pic":"http://www.yanglanzi8.com//uploadfiles/goods3.png","memo":null}]
     * specs : [{"id":"1","spec":"白色XL","stocks":"100","price":"11","promote_price":"11","goods_id":"1"}]
     * country : {"id":"1","name":"澳洲","flag_pic":"http://yidian2015.b0.upaiyun.com/uploadfiles/avatar/2016/05/17/1651710391815344.png"}
     * sale_method_name : 保税直发
     */

    private String sale_method_name;
    /**
     * pic : http://www.yanglanzi8.com//uploadfiles/goods1.png
     * memo : null
     */

    private List<BasePic> pics;
    /**
     * id : 1
     * spec : 白色XL
     * stocks : 100
     * price : 11
     * promote_price : 11
     * goods_id : 1
     */

    private List<Specs> specs;
    /**
     * description : null
     * favorited : 0
     */

    private Shop shop;

    private String description;
    private int favorited;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHuohao() {
        return huohao;
    }

    public void setHuohao(String huohao) {
        this.huohao = huohao;
    }

    public String getDefault_pic() {
        return default_pic;
    }

    public void setDefault_pic(String default_pic) {
        this.default_pic = default_pic;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(String promote_price) {
        this.promote_price = promote_price;
    }

    public String getPromote_begin_time() {
        return promote_begin_time;
    }

    public void setPromote_begin_time(String promote_begin_time) {
        this.promote_begin_time = promote_begin_time;
    }

    public String getPromote_end_time() {
        return promote_end_time;
    }

    public void setPromote_end_time(String promote_end_time) {
        this.promote_end_time = promote_end_time;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getGive_integral() {
        return give_integral;
    }

    public void setGive_integral(String give_integral) {
        this.give_integral = give_integral;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public String getStock_alert_number() {
        return stock_alert_number;
    }

    public void setStock_alert_number(String stock_alert_number) {
        this.stock_alert_number = stock_alert_number;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getExt_property() {
        return ext_property;
    }

    public void setExt_property(String ext_property) {
        this.ext_property = ext_property;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(String free_shipping) {
        this.free_shipping = free_shipping;
    }

    public String getCommission_template_id() {
        return commission_template_id;
    }

    public void setCommission_template_id(String commission_template_id) {
        this.commission_template_id = commission_template_id;
    }

    public String getCommissions() {
        return commissions;
    }

    public void setCommissions(String commissions) {
        this.commissions = commissions;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeller_note() {
        return seller_note;
    }

    public void setSeller_note(String seller_note) {
        this.seller_note = seller_note;
    }

    public String getIn_shop() {
        return in_shop;
    }

    public void setIn_shop(String in_shop) {
        this.in_shop = in_shop;
    }

    public String getSale_method() {
        return sale_method;
    }

    public void setSale_method(String sale_method) {
        this.sale_method = sale_method;
    }

    public String getSale_method_name() {
        return sale_method_name;
    }

    public void setSale_method_name(String sale_method_name) {
        this.sale_method_name = sale_method_name;
    }

    public List<BasePic> getPics() {
        return pics;
    }

    public void setPics(List<BasePic> pics) {
        this.pics = pics;
    }

    public List<Specs> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Specs> specs) {
        this.specs = specs;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSpec_price() {
        return spec_price;
    }

    public void setSpec_price(String spec_price) {
        this.spec_price = spec_price;
    }

    public String getSpec_promote_price() {
        return spec_promote_price;
    }

    public void setSpec_promote_price(String spec_promote_price) {
        this.spec_promote_price = spec_promote_price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorited() {
        return favorited;
    }

    public void setFavorited(int favorited) {
        this.favorited = favorited;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
