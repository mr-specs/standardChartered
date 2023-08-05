package com.standardDemo.standardChartered.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.standardDemo.standardChartered.entities.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	@Query(value="select * from user_accounts where user_id =:userId",nativeQuery = true)
	List<UserAccount> findById(@Param("userId")int userId);


}
