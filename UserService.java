package com.kapstonellc.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapstonellc.mapper.UserMapper;
import com.kapstonellc.model.Location;
import com.kapstonellc.model.Location_Db;
import com.kapstonellc.model.PersonLocationAssn;
import com.kapstonellc.model.User;
import com.kapstonellc.model.User_Db;

@Service
public class UserService {

	@Autowired
	UserMapper usermapper;

	public int insertUser(User user) {
		return usermapper.insertUser(user);
	}

	public int insertLocation(Location location) {
		return usermapper.insertLocation(location);
	}

	public User_Db selectUserInfo(String username) {
		return usermapper.retriveUser(username);
	}

	public Location_Db selectLocationInfo(String locationName) {
		return usermapper.retriveLocation(locationName);
	}

	public int insertStatus(PersonLocationAssn status) {
		return usermapper.insertStatus(status);
	}

	public PersonLocationAssn selectStatusInfo(PersonLocationAssn status) {
		return usermapper.retriveStatus(status);
	}

	public int updateStatus(PersonLocationAssn status) {
		return usermapper.updateStatus(status);
	}

	public int updateActiveStatus(PersonLocationAssn status) {
		return usermapper.updateActiveStatus(status);
	}
	
	public Map<String,Integer> getcredentials(PersonLocationAssn status) {
		
		Map<String,Integer> ids=new HashMap<String,Integer>();
		try {
		String user_name=status.getUser_name();
		String location_name=status.getLocation_name();
		User_Db userInfo=usermapper.retriveUser(user_name);
		Location_Db lacationInfo=usermapper.retriveLocation(location_name);
		ids.put(status.getUser_name(), userInfo.getPerson_id());
		ids.put(status.getLocation_name(), lacationInfo.getLocation_id());
		}catch(Exception se) {
			se.printStackTrace();
		}
		return ids;
	}
}
