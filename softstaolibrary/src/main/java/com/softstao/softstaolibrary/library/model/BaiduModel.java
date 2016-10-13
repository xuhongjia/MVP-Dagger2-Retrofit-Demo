package com.softstao.softstaolibrary.library.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuhon on 2016/7/27.
 */
public class BaiduModel implements Serializable {

    /**
     * status : 0
     * result : [{"x":113.42718098408,"y":23.133111952595}]
     */

    private int status;
    /**
     * x : 113.42718098408
     * y : 23.133111952595
     */

    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
