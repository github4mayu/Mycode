package com.kapstonellc.model;

public class Location_Db {
	private String location_name;
	private String short_code;
	private String active;
	private int location_id;

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getShort_code() {
		return short_code;
	}

	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
