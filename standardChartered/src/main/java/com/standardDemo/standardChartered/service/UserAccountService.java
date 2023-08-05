package com.standardDemo.standardChartered.service;

import java.util.List;

import com.standardDemo.standardChartered.entities.UserAccount;

public interface UserAccountService {
	
	List<UserAccount> getUsersById(int userId);

}
