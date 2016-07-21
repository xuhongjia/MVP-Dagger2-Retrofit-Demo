/**     
* PageViewAdapter.java Create on 2011-5-30    
*     
* Copyright (c) 2011-5-30 by whty  
*     
* @author xjin    
* @version 1.0
*    
*/ 
package com.softstao.softstaolibrary.library.widget.face;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import java.util.List;


public class PageViewAdapter extends ArrayAdapter<Channel> {

	private PageView.OnFaceItemListener faceItemListener;
	/**
	 * @param context
	 * @param list
	 */
	public PageViewAdapter(Context context, List<Channel> list) {
		super(context, 0, list);
	}
	
	public PageViewAdapter(Context context, List<Channel> list, PageView.OnFaceItemListener faceItemListener ) {
		super(context, 0, list);
		this.faceItemListener=faceItemListener;
	}
	
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Channel channel = getItem(position);
		if (null == convertView) {
			convertView = new PageView(getContext());
		}
		((PageView)convertView).setPageView(channel);
		((PageView)convertView).setFaceItemListener(faceItemListener);
		return convertView;
	}

}
 