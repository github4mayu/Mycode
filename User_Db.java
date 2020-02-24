package com.kapstonellc.model;

public class User_Db {
	private String user_name;
	private String first_name;
	private String last_name;
	private int    person_id;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	@Override
	public String toString() {
		return "User2 [user_name=" + user_name + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", person_id=" + person_id + "]";
	}

	
	
}
