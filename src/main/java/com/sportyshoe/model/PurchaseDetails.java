package com.sportyshoe.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PurchaseDetails {
	private String userName;
	private String userFirstname;
	private String userLastname;
	private String productName;
	private long categoryId;
	private String categoryName;
	private BigDecimal productAmount;
	private BigDecimal productPayment;
	private long productQuantity;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	public long getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserFirstname() {
		return userFirstname;
	}
	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}
	public String getUserLastname() {
		return userLastname;
	}
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public BigDecimal getProductPayment() {
		return productPayment;
	}
	public void setProductPayment(BigDecimal productPayment) {
		this.productPayment = productPayment;
	}
	
	

}
