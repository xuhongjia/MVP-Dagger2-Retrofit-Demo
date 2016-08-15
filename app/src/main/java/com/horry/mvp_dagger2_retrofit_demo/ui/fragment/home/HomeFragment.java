package com.horry.mvp_dagger2_retrofit_demo.ui.fragment.home;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.horry.mvp_dagger2_retrofit_demo.AppComponent;
import com.horry.mvp_dagger2_retrofit_demo.R;
import com.horry.mvp_dagger2_retrofit_demo.model.User;
import com.horry.mvp_dagger2_retrofit_demo.model.goods.Goods;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Home;
import com.horry.mvp_dagger2_retrofit_demo.model.home.Product;
import com.horry.mvp_dagger2_retrofit_demo.ui.activity.viewer.home.HomeViewer;
import com.horry.mvp_dagger2_retrofit_demo.ui.adapter.GoodsAdapter;
import com.horry.mvp_dagger2_retrofit_demo.ui.fragment.BaseFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
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

    private ArrayList<BasePic> data = new ArrayList<>();
    private List<Product> pics = new ArrayList<>();
    private List<Goods> goodses = new ArrayList<>();
    private RecycleViewBaseAdapter<Product> adapter;
    private GoodsAdapter goodsAdapter;
    private Home home;

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
        adapter = new RecycleViewBaseAdapter<Product>(pics, R.layout.category_item) {
            @Override
            public void convert(RecycleViewHolder holder, Product product) {
                holder.setText(R.id.name, product.getName());
                ImageView imageView = holder.getView(R.id.img);
                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                lp.height = getScreenWidth(getContext()) / 4 - LZUtils.dipToPix(getContext(), 40);
                lp.width = getScreenWidth(getContext()) / 4 - LZUtils.dipToPix(getContext(), 40);
                ImageLoader.getInstance().displayImage(product.getPic(), imageView);
//                ((ImageView)holder.getView(R.id.img)).setImageDrawable(getResources().getDrawable(Integer.parseInt(product.getPic())));
            }
        };
        category.setAdapter(adapter);
        category.setLayoutManager(new FullyGridLayoutManager(getContext(), 4));
        goodsAdapter = new GoodsAdapter(goodses);
        goodsList.setAdapter(goodsAdapter);
        goodsList.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
        goodsList.addItemDecoration(new MarginDecoration(getContext()));
        tintManager.setStatusBarTintColor(Color.TRANSPARENT);
    }

    //<!--softstao!-->
    //轮播图跳转
    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            ImageLoader.getInstance().displayImage(imageURL, imageView);
//            imageView.setImageDrawable(getResources().getDrawable(Integer.parseInt(imageURL)));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void onImageClick(Object info, int postion, View imageView) {
//            ((Flashes)info).getAd_type().equals("html5");
//            goToAdContent(home.getFlashes().get(postion));
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        if(adView!=null){
            adView.startImageCycle();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(adView!=null){
            adView.pushImageCycle();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(adView!=null){
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
        DaggerHomeFragementComponent
                .builder()
                .appComponent(appComponent)
                .homeFragmentModule(new HomeFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        super.loadData(pullToRefresh);
        presenter.getHome(currentPage,pullToRefresh);
    }


    @Override
    public void HomeReturn(Home home) {
        this.home = home;
        if (currentPage == 0) {
            goodses.clear();
            if (data.size() == 0 || !data.get(0).getId().equals(home.getFlashes().get(0).getId())) {
                data.clear();
                data.addAll(home.getFlashes());
                startAdView();
            }
        }
        if (home.getCategory() != null && home.getCategory().size() != pics.size()) {
            pics.clear();
            pics.addAll(home.getCategory());
            adapter.notifyDataSetChanged();
        }
        int size = goodses.size();
        goodses.addAll(home.getGoods());
        goodsAdapter.notifyItemInserted(size);
    }

    @Override
    public void resultMember(User user) {

    }

    protected void startAdView(){
        if(data!=null) {
            adView.setImageResources(data, mAdCycleViewListener);
        }
    }
}
