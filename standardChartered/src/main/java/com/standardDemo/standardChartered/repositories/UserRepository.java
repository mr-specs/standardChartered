package com.standardDemo.standardChartered.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.standardDemo.standardChartered.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value="select * from users where user_id =:userId",nativeQuery = true)
	List<User> findById(@Param("userId")int userId) ;
	
	@Query(value="select * from users where username =:username",nativeQuery = true)
	User findByUsername(@Param("username")String username);

    // You can add custom query methods here for specific operations.
}
