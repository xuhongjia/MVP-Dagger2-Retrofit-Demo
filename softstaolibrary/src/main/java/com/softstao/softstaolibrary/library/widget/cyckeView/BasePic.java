package com.softstao.softstaolibrary.library.widget.cyckeView;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/14.
 */
public class BasePic implements Serializable {
    public enum Type
    {
        HTML5,
        RECHARGE,
        TYPE,
        SUBTYPE,
        BRAND,
        COUNTRY,
        GOODS,
        SHOP,
        COUPON
    }

    /**
     * id : 6
     * name : 幻灯图3
     * ad_content : http://www.baidu.com
     * ad_type : html5
     * sort : 999
     * add_time : null
     * pic : http://www.yanglanzi8.com//uploadfiles/flash3.png
     * status : 1
     * begin_time : null
     * end_time : null
     * position : flash
     * channel : 0
     */

    private String id;
    private String name;
    private String ad_content;
    private String ad_type;
    private String sort;
    private String add_time;
    private String pic;
    private String status;
    private String begin_time;
    private String end_time;
    private String position;
    private String channel;

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

    public String getAd_content() {
        return ad_content;
    }

    public void setAd_content(String ad_content) {
        this.ad_content = ad_content;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
