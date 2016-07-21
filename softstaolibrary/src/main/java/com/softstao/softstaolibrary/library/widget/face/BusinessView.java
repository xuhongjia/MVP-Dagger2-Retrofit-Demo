package com.softstao.softstaolibrary.library.widget.face;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;


public class BusinessView extends Workspace{

	private BaseAdapter mAdapter;
	
	public BusinessView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BusinessView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setAdapter(BaseAdapter adapter){
		mAdapter = adapter;
		bindView();
	}
	
	private void bindView() {
		if (null != mAdapter) {
			int count = mAdapter.getCount();
			for (int i = 0; i < count; i++) {
				final View view = mAdapter.getView(i, null, null);
				addView(view, i);
			}
		}
	}
}
