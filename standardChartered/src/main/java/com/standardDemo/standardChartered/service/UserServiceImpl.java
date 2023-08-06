package com.standardDemo.standardChartered.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standardDemo.standardChartered.entities.User;
import com.standardDemo.standardChartered.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> allUsersFromRepo = null;
		try {
			allUsersFromRepo = userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allUsersFromRepo;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsersById(int userId) {
		List<User> userDetails = null;
		try {
			userDetails = userRepository.findById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

}
