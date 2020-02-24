package com.kapstonellc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kapstonellc.model.Location;
import com.kapstonellc.model.Location_Db;
import com.kapstonellc.model.PersonLocationAssn;
import com.kapstonellc.model.User;
import com.kapstonellc.model.User_Db;

@Mapper
public interface UserMapper {

	@Insert("INSERT INTO person (user_name,first_name,last_name)values(#{userName},#{firstName},#{lastName})")
	public int insertUser(User user);

	@Select("SELECT * FROM person where user_name=#{user_name}")
	public User_Db retriveUser(String user_name);
	
	@Insert("INSERT INTO location(location_name,short_code,active)values(#{locationName},#{shortCode},#{active})")
	public int insertLocation(Location location);

	@Select("SELECT * FROM location where location_name=#{location_name}")
	public Location_Db retriveLocation(String location_name);
	
	@Insert("INSERT INTO person_location_assn(person_id,location_id,active)values(#{person_id},#{location_id},#{active})")
	public int insertStatus(PersonLocationAssn status);
	
	@Update("UPDATE person_location_assn set active=#{active} WHERE person_id=#{person_id} and location_id=#{location_id}")
	public int updateStatus(PersonLocationAssn status);
	
	@Update("UPDATE person_location_assn set active=#{active} WHERE person_id=#{person_id} and active='true'")
	public int updateActiveStatus(PersonLocationAssn status);
	
	@Select("SELECT * FROM person_location_assn WHERE person_id=#{person_id} and location_id=#{location_id}")
	public PersonLocationAssn retriveStatus(PersonLocationAssn status);

}
