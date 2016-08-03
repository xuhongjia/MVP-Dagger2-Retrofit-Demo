package com.horry.mvp_dagger2_retrofit_demo.model.shop;



import com.horry.mvp_dagger2_retrofit_demo.model.home.Flashes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class Shop implements Serializable {

    /**
     * id : 3
     * name : fdsafdsfd
     * member_id : 0
     * avatar : null
     * poster_pic : null
     * goods : 0
     * resellers : 0
     * followers : 0
     * clicks : 6
     * province : null
     * city : null
     * district : null
     * address : null
     * mobile : null
     * tel : null
     * qq : null
     * weixin : null
     * email : null
     * contacter : null
     * type_id : 0
     * shop_type : 0
     * status : 1
     * add_time : 0
     * audit_time : null
     * description : null
     * return_province : null
     * return_city : null
     * return_district : null
     * return_address : null
     * return_name : null
     * return_mobile : null
     * orders : 0
     * week_orders : 0
     * total_income : 0.00
     * week_income : 0.00
     * can_withdraw_money : 0.00
     * withdrawed_money : 0.00
     * withdrawing_money : 0.00
     * longitude : 222
     * latitude : 22
     * sort : 999
     */

    private String id;
    private String name;
    private String member_id;
    private String avatar;
    private String poster_pic;
    private String goods;
    private String resellers;
    private String followers;
    private String clicks;
    private String province;
    private String city;
    private String district;
    private String address;
    private String mobile;
    private String tel;
    private String qq;
    private String weixin;
    private String email;
    private String contacter;
    private String type_id;
    private String shop_type;
    private String status;
    private String add_time;
    private String audit_time;
    private String description;
    private String return_province;
    private String return_city;
    private String return_district;
    private String return_address;
    private String return_name;
    private String return_mobile;
    private String orders;
    private String week_orders;
    private String total_income;
    private String week_income;
    private String can_withdraw_money;
    private String withdrawed_money;
    private String withdrawing_money;
    private String longitude;
    private String latitude;
    private String sort;
    private List<Flashes> ads;
    /**
     * community_id : 0
     * idcard_verify : 0
     * factory_verify : 0
     * vip_verify : 0
     * enterprise_verify : 0
     * refund_verify : 0
     * cash_verify : 0
     * reseller_type : 0
     * views : 0
     * districts : null
     * return_goods_address : null
     * idcard_pic1 : null
     * idcard_pic2 : null
     * business_license_pic : null
     * enterprise_name : null
     * direct_sale_income : 0.00
     * second_sale_income : 0.00
     * legal_person_mobile : null
     */

    private String community_id;
    private String idcard_verify;
    private String factory_verify;
    private String vip_verify;
    private String enterprise_verify;
    private String refund_verify;
    private String cash_verify;
    private String reseller_type;
    private String views;
    private String districts;
    private String return_goods_address;
    private String idcard_pic1;
    private String idcard_pic2;
    private String business_license_pic;
    private String enterprise_name;
    private String direct_sale_income;
    private String second_sale_income;
    private Object legal_person_mobile;

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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPoster_pic() {
        return poster_pic;
    }

    public void setPoster_pic(String poster_pic) {
        this.poster_pic = poster_pic;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getResellers() {
        return resellers;
    }

    public void setResellers(String resellers) {
        this.resellers = resellers;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturn_province() {
        return return_province;
    }

    public void setReturn_province(String return_province) {
        this.return_province = return_province;
    }

    public String getReturn_city() {
        return return_city;
    }

    public void setReturn_city(String return_city) {
        this.return_city = return_city;
    }

    public String getReturn_district() {
        return return_district;
    }

    public void setReturn_district(String return_district) {
        this.return_district = return_district;
    }

    public String getReturn_address() {
        return return_address;
    }

    public void setReturn_address(String return_address) {
        this.return_address = return_address;
    }

    public String getReturn_name() {
        return return_name;
    }

    public void setReturn_name(String return_name) {
        this.return_name = return_name;
    }

    public String getReturn_mobile() {
        return return_mobile;
    }

    public void setReturn_mobile(String return_mobile) {
        this.return_mobile = return_mobile;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getWeek_orders() {
        return week_orders;
    }

    public void setWeek_orders(String week_orders) {
        this.week_orders = week_orders;
    }

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public String getWeek_income() {
        return week_income;
    }

    public void setWeek_income(String week_income) {
        this.week_income = week_income;
    }

    public String getCan_withdraw_money() {
        return can_withdraw_money;
    }

    public void setCan_withdraw_money(String can_withdraw_money) {
        this.can_withdraw_money = can_withdraw_money;
    }

    public String getWithdrawed_money() {
        return withdrawed_money;
    }

    public void setWithdrawed_money(String withdrawed_money) {
        this.withdrawed_money = withdrawed_money;
    }

    public String getWithdrawing_money() {
        return withdrawing_money;
    }

    public void setWithdrawing_money(String withdrawing_money) {
        this.withdrawing_money = withdrawing_money;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Flashes> getAds() {
        return ads;
    }

    public void setAds(List<Flashes> ads) {
        this.ads = ads;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getIdcard_verify() {
        return idcard_verify;
    }

    public void setIdcard_verify(String idcard_verify) {
        this.idcard_verify = idcard_verify;
    }

    public String getFactory_verify() {
        return factory_verify;
    }

    public void setFactory_verify(String factory_verify) {
        this.factory_verify = factory_verify;
    }

    public String getVip_verify() {
        return vip_verify;
    }

    public void setVip_verify(String vip_verify) {
        this.vip_verify = vip_verify;
    }

    public String getEnterprise_verify() {
        return enterprise_verify;
    }

    public void setEnterprise_verify(String enterprise_verify) {
        this.enterprise_verify = enterprise_verify;
    }

    public String getRefund_verify() {
        return refund_verify;
    }

    public void setRefund_verify(String refund_verify) {
        this.refund_verify = refund_verify;
    }

    public String getCash_verify() {
        return cash_verify;
    }

    public void setCash_verify(String cash_verify) {
        this.cash_verify = cash_verify;
    }

    public String getReseller_type() {
        return reseller_type;
    }

    public void setReseller_type(String reseller_type) {
        this.reseller_type = reseller_type;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getReturn_goods_address() {
        return return_goods_address;
    }

    public void setReturn_goods_address(String return_goods_address) {
        this.return_goods_address = return_goods_address;
    }

    public String getIdcard_pic1() {
        return idcard_pic1;
    }

    public void setIdcard_pic1(String idcard_pic1) {
        this.idcard_pic1 = idcard_pic1;
    }

    public String getIdcard_pic2() {
        return idcard_pic2;
    }

    public void setIdcard_pic2(String idcard_pic2) {
        this.idcard_pic2 = idcard_pic2;
    }

    public String getBusiness_license_pic() {
        return business_license_pic;
    }

    public void setBusiness_license_pic(String business_license_pic) {
        this.business_license_pic = business_license_pic;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getDirect_sale_income() {
        return direct_sale_income;
    }

    public void setDirect_sale_income(String direct_sale_income) {
        this.direct_sale_income = direct_sale_income;
    }

    public String getSecond_sale_income() {
        return second_sale_income;
    }

    public void setSecond_sale_income(String second_sale_income) {
        this.second_sale_income = second_sale_income;
    }

    public Object getLegal_person_mobile() {
        return legal_person_mobile;
    }

    public void setLegal_person_mobile(Object legal_person_mobile) {
        this.legal_person_mobile = legal_person_mobile;
    }
}
