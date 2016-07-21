package com.softstao.softstaolibrary.library.widget.face;

import java.io.Serializable;


public class Business implements Serializable {

	public static int Del_Type=-1;
	private static final long serialVersionUID = 1L;


	private int imgId;
	private String imgName;
	private String name;

	private int type;// -1的话是删除其他图标用的用的

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
}
