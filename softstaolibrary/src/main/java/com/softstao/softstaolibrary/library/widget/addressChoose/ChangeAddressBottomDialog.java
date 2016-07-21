package com.softstao.softstaolibrary.library.widget.addressChoose;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softstao.softstaolibrary.R;
import com.softstao.softstaolibrary.library.widget.wheelView.AbstractWheelTextAdapter;
import com.softstao.softstaolibrary.library.widget.wheelView.OnWheelChangedListener;
import com.softstao.softstaolibrary.library.widget.wheelView.OnWheelScrollListener;
import com.softstao.softstaolibrary.library.widget.wheelView.WheelView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ChangeAddressBottomDialog extends Dialog implements View.OnClickListener,OnWheelChangedListener {

	//省市区控件
	private WheelView wvProvince;
	private WheelView wvCitys;
	private WheelView wvArea;

	private TextView btnSure;//确定按钮
	private TextView btnMyinfoCancel;
	private Context context;//上下文对象

	private JSONArray mJsonObj;//存放地址信息的json对象

	private String[] mProvinceDatas;
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	private ArrayList<String> arrProvinces = new ArrayList<String>();
	private ArrayList<String> arrCitys = new ArrayList<String>();
	private ArrayList<String> arrAreas = new ArrayList<String>();

	private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter cityAdapter;
	private AddressTextAdapter areaAdapter;

	//选中的省市区信息
	private String strProvince;
	private String strCity ;
	private String strArea ;

	//回调方法
	private OnAddressCListener onAddressCListener;

	//显示文字的字体大小
	private int maxsize = 24;
	private int minsize = 14;

	public ChangeAddressBottomDialog(Context context) {
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_myinfo_changeaddress_bottom);

		wvProvince = (WheelView) findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) findViewById(R.id.wv_address_city);
		wvArea = (WheelView) findViewById(R.id.wv_address_area);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnMyinfoCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

		btnSure.setOnClickListener(this);
		wvProvince.addChangingListener(this);
		wvCitys.addChangingListener(this);
		wvArea.addChangingListener(this);
		btnMyinfoCancel.setOnClickListener(this);
		initJsonData();
		initDatas();
		initProvinces();
		provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
//		wvProvince.setCyclic(true);//设置内容循环
		wvProvince.setCurrentItem(getProvinceItem(strProvince));

		initCitys(mCitisDatasMap.get(strProvince));
		cityAdapter = new AddressTextAdapter(context, arrCitys, getCityItem(strCity), maxsize, minsize);
		wvCitys.setVisibleItems(5);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(getCityItem(strCity));
		
		initAreas(mAreaDatasMap.get(strCity));
		areaAdapter = new AddressTextAdapter(context, arrAreas, getAreaItem(strArea), maxsize, minsize);
		wvArea.setVisibleItems(5);
		wvArea.setViewAdapter(areaAdapter);
		wvArea.setCurrentItem(getAreaItem(strArea));
		wvProvince.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});
		wvCitys.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);
			}
		});
		wvArea.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {}
			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, areaAdapter);
			}
		});
	}

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		int length = mProvinceDatas.length;
		for (int i = 0; i < length; i++) {
			arrProvinces.add(mProvinceDatas[i]);
		}
	}

	/**
	 * 根据省会，生成该省会的所有城市
	 * 
	 * @param citys
	 */
	public void initCitys(String[] citys) {
		if (citys != null) {
			arrCitys.clear();
			int length = citys.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(citys[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get("广东省");
			arrCitys.clear();
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrCitys != null && arrCitys.size() > 0
				&& !arrCitys.contains(strCity)) {
			strCity = arrCitys.get(0);
		}
	}
	
	/**
	 * 根据城市，生成该城市的所有地区
	 * 
	 * @param areas
	 */
	public void initAreas(String[] areas) {
		if (areas != null) {
			arrAreas.clear();
			int length = areas.length;
			for (int i = 0; i < length; i++) {
				arrAreas.add(areas[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get("广东省");
			arrCitys.clear();
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrAreas != null && arrAreas.size() > 0
				&& !arrAreas.contains(strArea)) {
			strArea = arrAreas.get(0);
		}
	}


	/**
	 * 初始化地点
	 * 
	 * @param province
	 * @param city
	 */
	public void setAddress(String province, String city,String area) {
		if (province != null && province.length() > 0) {
			this.strProvince = province;
		}
		if (city != null && city.length() > 0) {
			this.strCity = city;
		}
		if (area != null && area.length() > 0) {
			this.strArea = area;
		}
	}

	/**
	 * 返回省会索引
	 */
	public int getProvinceItem(String province) {
		int size = arrProvinces.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.contains(arrProvinces.get(i))) {
				noprovince = false;
				break;
//				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			strProvince = "广东省";
			provinceIndex=19;
		}
		return provinceIndex;
	}

	/**
	 * 得到城市索引
	 */
	public int getCityItem(String city) {
		int size = arrCitys.size();
		int cityIndex = 0;
		boolean nocity = true;
		for (int i = 0; i < size; i++) {
			if (city.contains(arrCitys.get(i))) {
				nocity = false;
				break;
//				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		if (nocity) {
			strCity = "广州市";
			cityIndex=0;
		}
		return cityIndex;
	}
	
	//得到地区
	public int getAreaItem(String area) {
		int size = arrAreas.size();
		int cityIndex = 0;
		boolean nocity1 = true;
		for (int i = 0; i < size; i++) {
			if (area.contains(arrAreas.get(i))) {
				nocity1 = false;
				break;
//				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		if (nocity1) {
			strArea = "荔湾区";
			cityIndex= 0;
		}
		return cityIndex;
	}
	
	//根据省来更新wheel的状态
	private void updateCities()
	{
		String currentText = (String) provinceAdapter.getItemText(wvProvince.getCurrentItem());
		if(!strProvince.equals(currentText)){
			strProvince = currentText;
			setTextviewSize(currentText, provinceAdapter);
			String[] citys = mCitisDatasMap.get(currentText);
			if (citys == null)
			{
				citys = new String[] { "" };
			}
			initCitys(citys);
			cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize, minsize);
			wvCitys.setViewAdapter(cityAdapter);
			wvCitys.setCurrentItem(0);
			updateAreas();
		}
	}
	
	//根据城市来更新wheel的状态
	private void updateAreas()
	{
		String currentText = (String) cityAdapter.getItemText(wvCitys.getCurrentItem());
		if(!strCity.equals(currentText)){
			strCity = currentText;
			setTextviewSize(currentText, cityAdapter);
			String[] areas = mAreaDatasMap.get(currentText);
			if (areas == null)
			{
				areas = new String[] { "" };
			}
			strArea = areas[0];
			initAreas(areas);
			areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
			wvArea.setViewAdapter(areaAdapter);
			wvArea.setCurrentItem(0);
		}
	}
	
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == wvProvince)
		{
			//切换省份的操作
			updateCities();
		} else if (wheel == wvCitys)
		{
			updateAreas();
		} else if (wheel == wvArea)
		{
			String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
			strArea = currentText;
			strArea = mAreaDatasMap.get(strCity)[newValue];
			setTextviewSize(currentText, areaAdapter);
		}
	}
	
////////////////////////////////////////////////////华丽的分界线	
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = context.getAssets().open("address.json");
			int len = -1;
			byte[] buf = new byte[is.available()];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "gbk"));
			}
			is.close();
			mJsonObj = new JSONArray(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void initDatas()
	{
		try
		{
			JSONArray jsonArray = mJsonObj;
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("name");// 省名字

				mProvinceDatas[i] = province;

				JSONArray jsonCs = null;
				try
				{
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("area");
				} catch (Exception e1)
				{
					continue;
				}
				String[] mCitiesDatas = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++)
				{
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("name");// 市名字
					mCitiesDatas[j] = city;
					JSONArray jsonAreas = null;
					try
					{
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("area");
					} catch (Exception e)
					{
						continue;
					}

					String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++)
					{
						String area = jsonAreas.getJSONObject(k).getString("name");// 区域的名称
						mAreasDatas[k] = area;
					}
					mAreaDatasMap.put(city, mAreasDatas);
				}

				mCitisDatasMap.put(province, mCitiesDatas);
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		mJsonObj = null;
	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	public interface OnAddressCListener {
		public void onClick(String province, String city, String area);
	}
	
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}


	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View v) {
		if (v == btnSure) {
			if (onAddressCListener != null) {
				onAddressCListener.onClick(strProvince, strCity,strArea);
			}
		}
		dismiss();
	}
}