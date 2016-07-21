package com.softstao.softstaolibrary.library.widget.face;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class EmotionJson {
    private List<Business> businessList;
    private Gson gson = new Gson();
    public EmotionJson(Context context){
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is =context.getAssets().open("expression.json");
            int len = -1;
            byte[] buf = new byte[is.available()];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "gbk"));
            }
            is.close();
            businessList = gson.fromJson(sb.toString(),new TypeToken<List<Business>>(){}.getType()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Business business : businessList){
            try {
//                Field field = R.mipmap.class.getDeclaredField(business.getImgName());
                int resourceId = context.getResources().getIdentifier(business.getImgName(), "mipmap",
                        context.getPackageName());
                business.setImgId(resourceId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }
}
