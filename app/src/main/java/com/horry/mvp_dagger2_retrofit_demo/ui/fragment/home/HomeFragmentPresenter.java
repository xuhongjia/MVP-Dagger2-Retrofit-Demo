package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;

import com.horry.mvp_dagger2_retrofit_demo.data.ModelHelperImpl;
import com.horry.mvp_dagger2_retrofit_demo.model.goods.Goods;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Product;
import com.horry.mvp_dagger2_retrofit_demo.BasePresenter;
import com.softstao.softstaolibrary.library.widget.cyckeView.BasePic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by xuhon on 2016/8/3.
 */
public class HomeFragmentPresenter extends BasePresenter<HomeFragment> {

    private ArrayList<BasePic> data = new ArrayList<>();
    private List<Product> pics = new ArrayList<>();
    private List<Goods> goodses = new ArrayList<>();
    private int goodsSize=0;
    private int picSize=0;
    /**
     * 构造方法
     * @param helper
     */
    @Inject
    public HomeFragmentPresenter(ModelHelperImpl helper) {
        super(helper);
    }

    public void getHome(int currentPage,boolean pullToRefresh){
        this.currentPage=currentPage;
        subscribe(helper.getHome(currentPage),home -> {
            if (home == null) {
                viewer.isEmpty();
            } else if (home.getGoods() == null ||home.getGoods().size()==0) {
                viewer.noMoreData();
            } else {
                goodsSize = goodses.size();
                if(currentPage==0){
                    goodses.clear();
                    if (data.size() == 0 || !data.get(0).getPic().equals(home.getFlashes().get(0).getPic())){
                        data.clear();
                        data.addAll(home.getFlashes());
                    }
                    if (home.getCategory() != null && home.getCategory().size() != pics.size()) {
                        picSize=pics.size();
                        pics.clear();
                        pics.addAll(home.getCategory());
                    }
                }
                goodses.addAll(home.getGoods());
                viewer.HomeReturn();
            }
        },pullToRefresh);
    }

    public void getMember(){

    }

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }

    public List<Product> getPics() {
        return pics;
    }

    public void setPics(List<Product> pics) {
        this.pics = pics;
    }

    public ArrayList<BasePic> getData() {
        return data;
    }

    public void setData(ArrayList<BasePic> data) {
        this.data = data;
    }

    public int getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(int goodsSize) {
        this.goodsSize = goodsSize;
    }

    public int getPicSize() {
        return picSize;
    }

    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }
}
