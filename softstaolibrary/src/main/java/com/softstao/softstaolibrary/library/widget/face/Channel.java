package com.softstao.softstaolibrary.library.widget.face;

import java.io.Serializable;
import java.util.List;

public class Channel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5016588086937847175L;

	private List<Business> businesses;

	private int id;
	private String name;
	private int type;
	public List<Business> getBusinesses() {
		return businesses;
	}
	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	
	
}
