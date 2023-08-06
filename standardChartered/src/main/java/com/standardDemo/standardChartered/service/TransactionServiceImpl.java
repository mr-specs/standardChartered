package com.standardDemo.standardChartered.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standardDemo.standardChartered.entities.ResponseTransModel;
import com.standardDemo.standardChartered.entities.TransactionDet;
import com.standardDemo.standardChartered.entities.UserAccount;
import com.standardDemo.standardChartered.repositories.TransactionRepository;
import com.standardDemo.standardChartered.repositories.UserAccountRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

/*	@Override
	public ResponseTransModel getTransactionDetById(int accountId, int userId) {
		ResponseTransModel responseTransModel = new ResponseTransModel();
		try {
			List<TransactionDet> transactionDet = transactionRepository.findByAccountAndUserId(accountId, userId);
			for (TransactionDet transactionObj : transactionDet) {
				transactionObj.setTransDate(this.convertDateFormat(transactionObj.getTransactionDate().toString()));
			}
			responseTransModel.setTransactinDetList(transactionDet);
			Double availableBal = transactionDet.get(transactionDet.size() - 1).getAvailableBalance();
			responseTransModel.setFinalAvailBalance(availableBal);
			Double currentBal = transactionDet.get(transactionDet.size() - 1).getCurrentBalance();
			responseTransModel.setFinalBalance(currentBal);
			List<UserAccount> userAccountDet = userAccountRepository.findById(userId);
			List<String> userAccountNumber = new ArrayList<>();
			for (UserAccount userAccountobj : userAccountDet) {
				userAccountNumber.add(userAccountobj.getAccountNumber());
			}
			responseTransModel.setAccountNumber(userAccountNumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseTransModel;

	}*/

	public String convertDateFormat(String transactionDate) {
		String inputDate = transactionDate;
		String outputFormat = "dd-MMM";
		String outputDate = null;

		try {
			// Define input and output date formats
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
			// Parse the input date string to a Date object
			Date date = inputDateFormat.parse(inputDate);
			// Format the Date object to the desired output format
			outputDate = outputDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputDate;
	}

	@Override
	public ResponseTransModel getTransactionDetByAccountNumber(Long accountNumber) {
		ResponseTransModel responseTransModel = new ResponseTransModel();
		try {
			List<TransactionDet> transactionDet = transactionRepository.findByAccountNumber(accountNumber);
			for (TransactionDet transactionObj : transactionDet) {
				transactionObj.setTransDate(this.convertDateFormat(transactionObj.getTransactionDate().toString()));
			}
			responseTransModel.setTransactinDetList(transactionDet);
			if (!transactionDet.isEmpty()) {
				Double availableBal = transactionDet.get(transactionDet.size() - 1).getAvailableBalance();
				responseTransModel.setFinalAvailBalance(availableBal);

				Double currentBal = transactionDet.get(transactionDet.size() - 1).getCurrentBalance();
				responseTransModel.setFinalBalance(currentBal);

				List<UserAccount> userAccountDet = userAccountRepository.findById(transactionDet.get(0).getUserId());

				List<String> userAccountNumber = new ArrayList<>();
				for (UserAccount userAccountobj : userAccountDet) {
					userAccountNumber.add(userAccountobj.getAccountNumber());
				}
				if (!userAccountNumber.isEmpty() && userAccountNumber.contains(String.valueOf(accountNumber))) {
					userAccountNumber.remove(String.valueOf(accountNumber)); // Remove data from its current position
					userAccountNumber.add(0, String.valueOf(accountNumber)); // Move data to the zero index
				}
				responseTransModel.setAccountNumber(userAccountNumber);
			} else {
				List<UserAccount> userAccountDet = userAccountRepository.findByAccountNumber(accountNumber);
				List<String> userAccountNumber = new ArrayList<>();
				for (UserAccount userAccountobj : userAccountDet) {
					userAccountNumber.add(userAccountobj.getAccountNumber());
				}
				if (userAccountNumber.contains(String.valueOf(accountNumber))) {
					userAccountNumber.remove(String.valueOf(accountNumber)); // Remove data from its current position
					userAccountNumber.add(0, String.valueOf(accountNumber)); // Move data to the zero index
				}
				responseTransModel.setAccountNumber(userAccountNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseTransModel;
	}

}
