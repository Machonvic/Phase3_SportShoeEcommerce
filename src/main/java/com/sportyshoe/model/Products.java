package com.sportyshoe.model;

import java.math.BigDecimal;

public class Products {
	
	private long productId;
	private String productName;
	private String productDesc;
	private String productCategoryID;
	private BigDecimal productPrice;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductCategoryID() {
		return productCategoryID;
	}
	public void setProductCategoryID(String productCategoryID) {
		this.productCategoryID = productCategoryID;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
