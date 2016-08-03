package com.horry.mvp_dagger2_retrofit_demo.model.goods;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Pics implements Serializable{
    private String pic;
    private String memo;

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
}
