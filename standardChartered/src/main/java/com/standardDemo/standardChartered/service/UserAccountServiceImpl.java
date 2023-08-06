package com.standardDemo.standardChartered.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standardDemo.standardChartered.entities.UserAccount;
import com.standardDemo.standardChartered.repositories.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	public List<UserAccount> getUsersById(int userId) {
		List<UserAccount> userDetails = null;
		try {
			userDetails = userAccountRepository.findById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

}
