package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.data.api.ApiService;
import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.model.goods.Goods;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Product;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.HomeViewer;
import com.horry.mvp_dagger2_retrofit_demo.ui.adapter.GoodsAdapter;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.BaseFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.softstao.softstaolibrary.library.mvp.animator.DefaultAnimator;
import com.softstao.softstaolibrary.library.mvp.baseAdapter.RecycleViewBaseAdapter;
import com.softstao.softstaolibrary.library.mvp.baseAdapter.RecycleViewHolder;
import com.softstao.softstaolibrary.library.utils.LZUtils;
import com.softstao.softstaolibrary.library.widget.FullyGridLayoutManager;
import com.softstao.softstaolibrary.library.widget.MarginDecoration;
import com.softstao.softstaolibrary.library.widget.cyckeView.BasePic;
import com.softstao.softstaolibrary.library.widget.cyckeView.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.squareup.leakcanary.ExcludedRefs.builder;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeViewer {

    @BindView(R.id.category)
    RecyclerView category;
    @BindView(R.id.goods_list)
    RecyclerView goodsList;
    @Inject
    HomeFragmentPresenter presenter;
    @BindView(R.id.ad_view)
    ImageCycleView adView;

    private RecycleViewBaseAdapter<Product> adapter;
    private GoodsAdapter goodsAdapter;

    @Override
    public int _setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        initTitle("首页");
        setChange(true);
        changeContentRule(RelativeLayout.BELOW,0);
        setPtrFrameLayoutEnable();
        adapter = new RecycleViewBaseAdapter<Product>(presenter.getPics(), R.layout.category_item) {
            @Override
            public void convert(RecycleViewHolder holder, Product product) {
                holder.setText(R.id.name, product.getName());
                ImageView imageView = holder.getView(R.id.img);
                int width = getScreenWidth(getContext()) / 4 - LZUtils.dipToPix(getContext(), 40);
                Glide.with(mContext)
                        .load(product.getPic()).diskCacheStrategy(DiskCacheStrategy.ALL).override(width,width).into(imageView);
            }
        };
        category.setAdapter(adapter);
        category.setLayoutManager(new FullyGridLayoutManager(getContext(), 4));
        ((SimpleItemAnimator)category.getItemAnimator()).setSupportsChangeAnimations(false);
        goodsAdapter = new GoodsAdapter(presenter.getGoodses());
        goodsList.setAdapter(goodsAdapter);
        goodsList.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
        goodsList.addItemDecoration(new MarginDecoration(getContext()));
        ((SimpleItemAnimator)goodsList.getItemAnimator()).setSupportsChangeAnimations(false);
        tintManager.setStatusBarTintColor(Color.TRANSPARENT);
        startAdView();
    }

    //轮播图跳转
    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            Glide.with(mContext)
                    .load(imageURL).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(imageView);
        }

        @Override
        public void onImageClick(Object info, int postion, View imageView) {
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        if(adView!=null&&presenter.getData().size()>0){
            adView.startImageCycle();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(adView!=null&&presenter.getData().size()>0){
            adView.pushImageCycle();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(adView!=null&&presenter.getData().size()>0){
            if(isVisibleToUser){
                adView.startImageCycle();
            }
            else{
                adView.pushImageCycle();
            }
        }
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
//        appComponent.inject(this);
        DaggerHomeFragementComponent
                .builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        presenter.setViewer(this);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        super.loadData(pullToRefresh);
        presenter.getHome(currentPage,pullToRefresh);
    }


    @Override
    public void HomeReturn() {
        if (currentPage == 0) {
            if(adView.getData().size()==0) {startAdView();}
            if(adView.getmAdvAdapter()!=null) {adView.getmAdvAdapter().notifyDataSetChanged();}
            adapter.notifyItemRangeRemoved(0,presenter.getPicSize());
            adapter.notifyItemRangeInserted(0,presenter.getPics().size());
            goodsAdapter.notifyItemRangeRemoved(0,presenter.getGoodsSize());
        }
        goodsAdapter.notifyItemRangeInserted(presenter.getGoodses().size()-ApiService.pageSize, ApiService.pageSize);
    }

    @Override
    public void resultMember(User user) {

    }

    protected void startAdView(){
        if(presenter.getData().size()>0) {
            adView.setImageResources(presenter.getData(), mAdCycleViewListener);
        }
    }
}
