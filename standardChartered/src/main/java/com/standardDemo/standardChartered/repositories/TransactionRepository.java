package com.standardDemo.standardChartered.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.standardDemo.standardChartered.entities.TransactionDet;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDet, Long> {
	
	@Query(value="select * from transactions where account_id =:accountId and user_id =:userId",nativeQuery = true)
	List<TransactionDet> findByAccountAndUserId(@Param("accountId")int accountId, @Param("userId")int userId);
}
