package com.horry.mvp_dagger2_retrofit_demo.model.home;


import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class Product {

    /**
     * id : 1
     * name : 进口零食
     * parent_id : 0
     * pic : null
     * memo : null
     * keywords : null
     * status : 1
     * sort : 999
     * categories : [{"id":"8","name":"休闲零食","parent_id":"1","pic":null,"memo":null,"keywords":null,"status":"1","sort":"999"},{"id":"7","name":"酒水饮料","parent_id":"1","pic":null,"memo":null,"keywords":null,"status":"1","sort":"999"},{"id":"6","name":"生鲜食品","parent_id":"1","pic":null,"memo":null,"keywords":null,"status":"1","sort":"999"}]
     */

    private String id;
    private String name;
    private String parent_id;
    private String pic;
    private String memo;
    private String keywords;
    private String status;
    private String sort;
    /**
     * id : 8
     * name : 休闲零食
     * parent_id : 1
     * pic : null
     * memo : null
     * keywords : null
     * status : 1
     * sort : 999
     */

    private List<Categories> categories;

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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
