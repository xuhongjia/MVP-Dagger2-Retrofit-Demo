package com.softstao.softstaolibrary.library.widget.face;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.softstao.softstaolibrary.R;

import java.util.List;

public class PageView extends LinearLayout {
	
	
	public interface OnFaceItemListener {
		void clickItem(Business business);
	}
	
	public void setFaceItemListener(OnFaceItemListener faceItemListener) {
		this.faceItemListener = faceItemListener;
	}

	private OnFaceItemListener faceItemListener;
	private GridView mGridView;
	public static final String TAB_WIDGET = "tab_widget";
	private GridAdapter adapter;
	private Context mContext;
	public static final int APP_DOWN_MSG = 0;
	public static final int APP_UPDATE_MSG = 1;

	
	
	
	/**
	 * @param context
	 */
	public PageView(Context context) {
		this(context, null);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public PageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		inflate(getContext(), R.layout.face_base_layout, this);
		initView();
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridView1);
	}

	/**
	 * @param channel
	 */
	public void setPageView(Channel channel) {
		List<Business> businessList = channel.getBusinesses();
		adapter = new GridAdapter(getContext(), businessList);
		mGridView.setAdapter(adapter);
		mGridView.setOnItemClickListener(mOnItemClickListener);
	}

	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			Business business = (Business) parent.getAdapter()
					.getItem(position);
			if(null!=faceItemListener){
				faceItemListener.clickItem(business);
			}
		}
	};

	public void notifyAdapter() {
		adapter.notifyDataSetChanged();
	}

}
