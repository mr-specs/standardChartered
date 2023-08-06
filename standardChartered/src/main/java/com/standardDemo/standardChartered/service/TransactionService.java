package com.standardDemo.standardChartered.service;

import com.standardDemo.standardChartered.entities.ResponseTransModel;

public interface TransactionService {
	
//	ResponseTransModel  getTransactionDetById(int accountId, int userId);

	ResponseTransModel getTransactionDetByAccountNumber(Long accountNumber);

}
