package com.standardDemo.standardChartered.sevices;

import java.util.List;

import com.standardDemo.standardChartered.entities.User;


public interface UserService {

	List<User> getAllUsers();
	
	List<User> getUsersById(int userId);

	User findByUsername(String username);


}
