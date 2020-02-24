package com.kapstonellc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kapstonellc.model.Location;
import com.kapstonellc.model.Location_Db;
import com.kapstonellc.model.Location_Response;
import com.kapstonellc.model.PersonLocationAssn;
import com.kapstonellc.model.Status_Response;
import com.kapstonellc.model.User;
import com.kapstonellc.model.User_Db;
import com.kapstonellc.model.User_Response;
import com.kapstonellc.service.UserService;

@RestController
public class MainController {

	@Autowired
	UserService userservice;

//	@Autowired(required = false)
//	@Qualifier("/user_resp")
//	User_Response user_resp;

//	@Autowired(required = false)
//	@Qualifier("/location_resp")
//	Location_Response location_resp;

	
//	@Autowired(required = false)
//	@Qualifier("/status_resp")
//	Status_Response status_resp;

	@PostMapping("user/save")
	public ResponseEntity<User_Response> insertUserData(@RequestBody User user) {

		User_Response user_resp = new User_Response();
		int count = 0;
		User_Db existing_user = userservice.selectUserInfo(user.getUserName());
		if (existing_user == null) {
			count = userservice.insertUser(user);
		}

		if (count != 0) {
			user_resp.setUserName(user.getUserName());
			user_resp.setStatusCode(200);
			user_resp.setMessage("User Created Successfully");
			return new ResponseEntity<User_Response>(user_resp, HttpStatus.OK);
		} else {
			user_resp.setUserName(user.getUserName());
			user_resp.setStatusCode(409);
			user_resp.setMessage(user.getUserName() + " already exit");
			return new ResponseEntity<User_Response>(user_resp, HttpStatus.CONFLICT);
		}
	}//user

	@PostMapping("location/save")
	public ResponseEntity<Location_Response> insertLocationData(@RequestBody Location location) {
		Location_Response location_resp = new Location_Response();
		int count = 0;
		Location_Db existing_location = userservice.selectLocationInfo(location.getLocationName());
		if (existing_location == null) {
			count = userservice.insertLocation(location);
		}

		if (count != 0) {
			location_resp.setLocationName(location.getLocationName());
			location_resp.setStatusCode(200);
			location_resp.setMessage("Location Created Successfully");
			return new ResponseEntity<Location_Response>(location_resp, HttpStatus.OK);
		} else {
			location_resp.setLocationName(location.getLocationName());
			location_resp.setStatusCode(409);
			location_resp.setMessage(location.getLocationName() + " already exit");
			return new ResponseEntity<Location_Response>(location_resp, HttpStatus.CONFLICT);
		}
	}//location

	@PostMapping("person/location/save")
	public ResponseEntity<Status_Response> insertstatusData(@RequestBody PersonLocationAssn status) {
		Status_Response status_resp = new Status_Response();
		int count = 0, count_1 = 0;
		Map<String, Integer> ids = userservice.getcredentials(status);
		status.setPerson_id(ids.get(status.getUser_name()));
		status.setLocation_id(ids.get(status.getLocation_name()));
		PersonLocationAssn existing_status = userservice.selectStatusInfo(status);

		if (existing_status == null) {
			status.setActive("false");
			userservice.updateActiveStatus(status);
			status.setActive("true");
			count = userservice.insertStatus(status);
		} else {
			if (existing_status.getActive().equals("false")) {
				status.setActive("false");
				userservice.updateActiveStatus(status);
				status.setActive("true");
				count_1 = userservice.updateStatus(status);
			}
		}
		if (count != 0) {
			status_resp.setUserName(status.getUser_name());
			status_resp.setLocationName(status.getLocation_name());
			status_resp.setStatusCode(200);
			status_resp.setMessage("Entry Created Successfully");
			return new ResponseEntity<Status_Response>(status_resp, HttpStatus.OK);
		} else if (count_1 != 0) {
			status_resp.setUserName(status.getUser_name());
			status_resp.setLocationName(status.getLocation_name());
			status_resp.setStatusCode(200);
			status_resp.setMessage("Entry Updated Successfully");
			return new ResponseEntity<Status_Response>(status_resp, HttpStatus.OK);
		} else {
			status_resp.setUserName(status.getUser_name());
			status_resp.setLocationName(status.getLocation_name());
			status_resp.setStatusCode(409);
			status_resp.setMessage(status.getUser_name() + "  " + status.getLocation_name() + " record not found");
			return new ResponseEntity<Status_Response>(status_resp, HttpStatus.CONFLICT);
		}
	}//single person_location_assn

	@PostMapping("users/save")
	public List<ResponseEntity<User_Response>> insertUserListData(@RequestBody List<User> users) {
		List<ResponseEntity<User_Response>> resp_list = new ArrayList<ResponseEntity<User_Response>>();
		for (User user : users) {
			User_Response user_resp = new User_Response();
			int count = 0;
			User_Db existing_user = userservice.selectUserInfo(user.getUserName());
			if (existing_user == null) {
				count = userservice.insertUser(user);
			}
			if (count != 0) {
				user_resp.setUserName(user.getUserName());
				user_resp.setStatusCode(200);
				user_resp.setMessage("User Created Successfully");
				resp_list.add(new ResponseEntity<User_Response>(user_resp, HttpStatus.OK));
//			return new ResponseEntity<User_Response>(user_resp, HttpStatus.OK);
			} else {
				user_resp.setUserName(user.getUserName());
				user_resp.setStatusCode(409);
				user_resp.setMessage(user.getUserName() + " already exit");
				resp_list.add(new ResponseEntity<User_Response>(user_resp, HttpStatus.CONFLICT));
//			return new ResponseEntity<User_Response>(user_resp, HttpStatus.CONFLICT);
			}
		}
		return resp_list;
	}//users
	
	@PostMapping("locations/save")
	public List<ResponseEntity<Location_Response>> insertLocationsData(@RequestBody List<Location> locations) {
		List<ResponseEntity<Location_Response>> resp_list = new ArrayList<ResponseEntity<Location_Response>>();
		for(Location location:locations) {
		Location_Response location_resp = new Location_Response();
		int count = 0;
		Location_Db existing_location = userservice.selectLocationInfo(location.getLocationName());
		if (existing_location == null) {
			count = userservice.insertLocation(location);
		}

		if (count != 0) {
			location_resp.setLocationName(location.getLocationName());
			location_resp.setStatusCode(200);
			location_resp.setMessage("Location Created Successfully");
			resp_list.add(new ResponseEntity<Location_Response>(location_resp, HttpStatus.OK));
//			return new ResponseEntity<Location_Response>(location_resp, HttpStatus.OK);
		} else {
			location_resp.setLocationName(location.getLocationName());
			location_resp.setStatusCode(409);
			location_resp.setMessage(location.getLocationName() + " already exit");
			resp_list.add(new ResponseEntity<Location_Response>(location_resp, HttpStatus.CONFLICT));
//			return new ResponseEntity<Location_Response>(location_resp, HttpStatus.CONFLICT);
		}
		}
		return resp_list;
	}//Locations
	
	@PostMapping("persons/locations/save")
	public List<ResponseEntity<Status_Response>> insertMultipleStatus(@RequestBody List<PersonLocationAssn> statusList) {
		List<ResponseEntity<Status_Response>> resp_list = new ArrayList<ResponseEntity<Status_Response>>();
		
		for(PersonLocationAssn status: statusList){
		Status_Response status_resp = new Status_Response();
		int count = 0, count_1 = 0;
		Map<String, Integer> ids = userservice.getcredentials(status);
		status.setPerson_id(ids.get(status.getUser_name()));
		status.setLocation_id(ids.get(status.getLocation_name()));
		PersonLocationAssn existing_status = userservice.selectStatusInfo(status);

		if (existing_status == null) {
			status.setActive("false");
			userservice.updateActiveStatus(status);
			status.setActive("true");
			count = userservice.insertStatus(status);
		} else {
			if (existing_status.getActive().equals("false")) {
				status.setActive("false");
				userservice.updateActiveStatus(status);
				status.setActive("true");
				count_1 = userservice.updateStatus(status);
			}
		}
		if (count != 0) {
			status_resp.setUserName(status.getUser_name());
			status_resp.setLocationName(status.getLocation_name());
			status_resp.setStatusCode(200);
			status_resp.setMessage("Entry Created Successfully");
			resp_list.add(new ResponseEntity<Status_Response>(status_resp, HttpStatus.OK));
//			return new ResponseEntity<Status_Response>(status_resp, HttpStatus.OK);
		} else if (count_1 != 0) {
			status_resp.setUserName(status.getUser_name());
			status_resp.setLocationName(status.getLocation_name());
			status_resp.setStatusCode(200);
			status_resp.setMessage("Entry Updated Successfully");
			resp_list.add(new ResponseEntity<Status_Response>(status_resp, HttpStatus.OK));
//			return new ResponseEntity<Status_Response>(status_resp, HttpStatus.OK);
		} else {
			status_resp.setUserName(status.getUser_name());
			status_resp.setLocationName(status.getLocation_name());
			status_resp.setStatusCode(409);
			status_resp.setMessage(status.getUser_name() + "  " + status.getLocation_name() + " record not found");
			resp_list.add(new ResponseEntity<Status_Response>(status_resp, HttpStatus.CONFLICT));
//			return new ResponseEntity<Status_Response>(status_resp, HttpStatus.CONFLICT);
		}
		
		}
		return resp_list;
	}//multiple person_location_assn

	
	
}// mainController
