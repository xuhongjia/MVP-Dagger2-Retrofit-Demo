package com.softstao.softstaolibrary.library.global;

import com.softstao.softstaolibrary.library.mvp.activity.MvpBaseActivity;

import java.util.LinkedList;

/**
 * Created by xuhon on 2016/7/30.
 */
public class AppManager {
    private static AppManager appManager;
    private LinkedList<MvpBaseActivity> activities = new LinkedList<>();
    private AppManager(){

    }

    public synchronized static AppManager getInstance(){
        if(appManager==null){
            appManager=new AppManager();
        }
        return appManager;
    }

    public void add(MvpBaseActivity activity){
        activities.add(activity);
    }

    public void remove(MvpBaseActivity activity){
        if(activities.remove(activity)){
            activity.finish();
            activity=null;
        }
    }

}
