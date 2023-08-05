package com.standardDemo.standardChartered.entities;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ResponseTransModel {

	private List<TransactionDet> transactinDetList;
	

    private Double finalBalance;
    
    private Double finalAvailBalance;
}
