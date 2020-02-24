package com.kapstonellc.model;

public class PersonLocationAssn {
	private String user_name;
	private String location_name;
	private int person_id;
	private int location_id;;
	private String active;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
