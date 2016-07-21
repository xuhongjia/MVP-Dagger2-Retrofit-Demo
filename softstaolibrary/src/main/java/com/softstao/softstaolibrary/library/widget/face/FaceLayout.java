package com.softstao.softstaolibrary.library.widget.face;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


import com.softstao.softstaolibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhon on 2016/5/22.
 */
public class FaceLayout extends LinearLayout {
    private PageViewAdapter adapter;
    BusinessView mBusinessView;
    private int[] imageIds;
    private String[] imageNames;
    private int length = 64;// 表情个数
    private final int index = 20;// 21个一页  这个1是加个删除的按钮
    private List<Channel> channels;
    LinearLayout dotLayout;
    private List<DotView> dots = new ArrayList<DotView>();
    private int oldPosition = 0;
    private Context mContext;
    private List<Business> businessList;
    PageView.OnFaceItemListener actfaceItemListener;
    public void setActfaceItemListener(PageView.OnFaceItemListener actfaceItemListener) {
        this.actfaceItemListener = actfaceItemListener;
    }

    public FaceLayout(Context context) {
        this(context, null);
    }

    public FaceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater factory = LayoutInflater.from(context);
        factory.inflate(R.layout.face_business_workspace, this);
        initView();
        new UpDataAsy().execute();
    }

    class UpDataAsy extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            channels = getChannelData();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            setUpView();

        }

    }

    private void setUpView() {
        // TODO Auto-generated method stub
        channels = getChannelData();
        mBusinessView.removeAllViews();
        adapter = new PageViewAdapter(getContext(), channels ,faceItemListener);
        initDot(channels.size());
        mBusinessView.setAdapter(adapter);
        mBusinessView.setCurrentScreen(0);
        mBusinessView.setFlowIndicator(new FlowIndicator() {

            @Override
            public void setWorkspace(Workspace view) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onWorkspaceScrollChanged(int h, int v, int oldh,
                                                 int oldv) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScreenChanged(View view, int position) {
                // TODO Auto-generated method stub
                dots.get(oldPosition).setDotBackground(R.mipmap.dot_dark);
                dots.get(position).setDotBackground(R.mipmap.dot_light);
                oldPosition = position;

            }
        });

    }

    /**
     * 监听点击事件
     */
    private PageView.OnFaceItemListener faceItemListener= new PageView.OnFaceItemListener() {

        @Override
        public void clickItem(Business business) {
            // TODO Auto-generated method stub
            if(null!=actfaceItemListener){
                actfaceItemListener.clickItem(business);
            }
        }
    };



    private void initDot(int count) {
        if (null == dots) {
            dots = new ArrayList<DotView>();
        } else {
            dots.clear();
        }

        for (int i = 0; i < count; i++) {
            DotView dotView = new DotView(getContext());
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 2;
            params.rightMargin = 2;
            if (i == 0) {
                dotView.setDotBackground(R.mipmap.dot_light);
            } else {
                dotView.setDotBackground(R.mipmap.dot_dark);
            }
            dots.add(dotView);
            dotLayout.addView(dotView, params);
        }

    }

    private List<Channel> getChannelData() {
        // TODO Auto-generated method stub
        if (null != channels && channels.size() > 0) {
            return channels;
        }
        businessList = new EmotionJson(mContext).getBusinessList();
        length = businessList.size();
//        int[] imageIds = getImgIds();
//        getImgIds();
        int m = length / index;
        if (length % index != 0) {
            m++;
        }
        Business delbs = new Business();//用来删除工作的
        delbs.setImgId(R.mipmap.del_btn2x);
        delbs.setType(Business.Del_Type);
        List<Channel> channels = new ArrayList<Channel>();
        for (int i = 0; i < m; i++) {
            if (length % index != 0 && i == m - 1) {
                int mc = (m - 1) * index;
                int rc = length - mc;
                Channel ch = new Channel();
                List<Business> businesses = new ArrayList<Business>();
                for (int j = i*index ; j < length; j++) {
//                    Business bs = new Business();
//                    bs.setImgId(imageIds[(i) * index + j]);
//                    bs.setName(imageNames[(i) * index + j]);
                    businesses.add(businessList.get(j));
                }
                businesses.add(delbs);//一页完了  加个删除图标
                ch.setBusinesses(businesses);
                channels.add(ch);
            } else {
                Channel ch = new Channel();
                List<Business> businesses = new ArrayList<Business>();
                for (int j = i*index; j < (i+1)*index; j++) {
//                    Business bs = new Business();
//                    bs.setImgId(imageIds[(i) * index + j]);
//                    bs.setName(imageNames[(i) * index + j]);
                    businesses.add(businessList.get(j));
                }
                businesses.add(delbs);//一页完了  加个删除图标
                ch.setBusinesses(businesses);
                channels.add(ch);
            }
        }

        return channels;
    }

    private void initView() {
        // TODO Auto-generated method stub
        mBusinessView = (BusinessView) findViewById(R.id.businessView);
        dotLayout = (LinearLayout) findViewById(R.id.dots_layout);

    }

//    private void getImgIds() {
////        if(null!=businessList&&businessList.size() == length) {
////            return ;
////        }
////        imageIds = new int[length];
////        imageNames= new String[length];
//        for(Business business : businessList){
//            try {
//                Field field = R.mipmap.class.getDeclaredField(business.getImgName());
//                int resourceId = Integer.parseInt(field.get(null)
//                        .toString());
//                business.setImgId(resourceId);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private int[] getImgIds() {
//        if (null != imageIds && imageIds.length == length) {
//
//            return imageIds;
//        }
//        imageIds = new int[length];
//        imageNames= new String[length];
//        for (int i = 0; i < length; i++) {
//            try {
//                if (i < 10) {
//                    Field field = R.mipmap.class.getDeclaredField("f00" + i);
//                    int resourceId = Integer.parseInt(field.get(null)
//                            .toString());
//                    imageIds[i] = resourceId;
//                    imageNames[i]="[f00" + i+"]";
//                } else if (i < 100) {
//                    Field field = R.mipmap.class.getDeclaredField("f0" + i);
//                    int resourceId = Integer.parseInt(field.get(null)
//                            .toString());
//                    imageIds[i] = resourceId;
//                    imageNames[i]="[f0" + i + "]";
//                } else {
//                    Field field = R.mipmap.class.getDeclaredField("f" + i);
//                    int resourceId = Integer.parseInt(field.get(null)
//                            .toString());
//                    imageIds[i] = resourceId;
//                    imageNames[i]="[f" + i+"]";
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return imageIds;
//    }
}
