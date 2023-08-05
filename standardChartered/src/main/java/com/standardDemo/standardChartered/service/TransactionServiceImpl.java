package com.standardDemo.standardChartered.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standardDemo.standardChartered.entities.ResponseTransModel;
import com.standardDemo.standardChartered.entities.TransactionDet;
import com.standardDemo.standardChartered.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService	{
	
	@Autowired
	TransactionRepository transactionRepository;
	

	@Override
	public ResponseTransModel getTransactionDetById(int accountId, int userId) {
		
		ResponseTransModel responseTransModel= new ResponseTransModel();
		
		List<TransactionDet>transactionDet =transactionRepository.findByAccountAndUserId(accountId,userId);
		
		
		for (TransactionDet transactionObj : transactionDet) {
			transactionObj.setTransDate(this.convertDateFormat(transactionObj.getTransactionDate().toString()));
		}
		
		responseTransModel.setTransactinDetList(transactionDet);
	 Double availableBal = transactionDet.get(transactionDet.size()-1).getAvailableBalance();
	 responseTransModel.setFinalAvailBalance(availableBal);	
	 
	 Double currentBal = transactionDet.get(transactionDet.size()-1).getCurrentBalance();
	 responseTransModel.setFinalBalance(currentBal);	
		
		return responseTransModel;
	}
	
	
	public String convertDateFormat(String a) {
		   String inputDate = a;
	        String outputFormat = "dd-MMM";
	        String outputDate =null;
	 

	        try {
	            // Define input and output date formats
	            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

	 

	            // Parse the input date string to a Date object
	            Date date = inputDateFormat.parse(inputDate);

	 

	            // Format the Date object to the desired output format
	            outputDate = outputDateFormat.format(date);

	 

	            System.out.println("Input Date: " + inputDate);
	            System.out.println("Output Date: " + outputDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return outputDate;
	}

}
