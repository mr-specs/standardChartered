package com.standardDemo.standardChartered.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standardDemo.standardChartered.entities.User;
import com.standardDemo.standardChartered.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService  {
	@Autowired
    UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User>allUsersFromRepo = userRepository.findAll();
	    System.out.println("Number of users retrieved: " + allUsersFromRepo.size());
	    for (User user : allUsersFromRepo) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Date of Birth: " + user.getDateOfBirth());
            System.out.println("Created At: " + user.getCreatedAt());
            System.out.println("-----------------------------------");
        }

		return allUsersFromRepo;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsersById(int userId) {
		// TODO Auto-generated method stub
		
		List<User> userDetails = userRepository.findById(userId);
		
		return userDetails;
	}
	 

   


}
