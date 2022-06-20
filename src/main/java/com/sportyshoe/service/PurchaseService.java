package com.sportyshoe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.DAO.PurchaseDAO;
import com.sportyshoe.model.PurchaseDetails;

@Service
public class PurchaseService {
	
	@Autowired
	PurchaseDAO purchaseDAO;

	public List<PurchaseDetails> getAllPurchaseDetails(long l,String purchaseDate) {
		
		return purchaseDAO.findAllPurchaseDetails(l,purchaseDate);
	}
	
	}
	
