package com.softstao.softstaolibrary.library.widget.face;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.softstao.softstaolibrary.R;


public class DotView extends FrameLayout {
	
	private View dotView;
	
	/**
	 * @param context
	 */
	public DotView(Context context) {
		this(context, null);
	}
	
	/**
	 * @param context
	 * @param attrs
	 */
	public DotView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DotView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflate(context, R.layout.dot_view, this);
		dotView = findViewById(R.id.dot);
	}
	
	public void setDotBackground(int id){
		dotView.setBackgroundResource(id);
	}

}
