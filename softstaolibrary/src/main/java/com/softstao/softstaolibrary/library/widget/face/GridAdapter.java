/**     
* GridAdapter.java Create on 2011-5-10    
*     
* Copyright (c) 2011-5-10 by whty  
*     
* @author xjin    
* @version 1.0
*    
*/ 
package com.softstao.softstaolibrary.library.widget.face;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import com.softstao.softstaolibrary.R;

import java.util.List;


/**
 * @author xjin
 */
public class GridAdapter extends ArrayAdapter<Business> {
	LayoutInflater mInflater;
	Context mContext;
    
	/**
	 * @param context
	 * @param list
	 */
	public GridAdapter(Context context, List<Business> list) {
		super(context, 0, list);
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Business business = getItem(position);
		if (null == convertView) {
			convertView = mInflater.inflate(R.layout.img_grid_item, null);
		}
		Holder h=(Holder) convertView.getTag();

		if(null==h){
			h=new Holder();
			h.img=(ImageView) convertView.findViewById(R.id.imageView1);
		}
		
		h.img.setImageResource(business.getImgId());
		
		convertView.setTag(h);
		
		return convertView;
	}
	
	static class Holder{
		ImageView img;
	}
}
