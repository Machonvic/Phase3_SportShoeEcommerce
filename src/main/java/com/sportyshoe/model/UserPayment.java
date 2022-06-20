package com.sportyshoe.model;

public class UserPayment {
	
	private long userPayid;
	private long userId;
	private String userAccount;
	private String userProvider;
	private String userExpiry;
	public long getUserPayid() {
		return userPayid;
	}
	public void setUserPayid(long userPayid) {
		this.userPayid = userPayid;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserProvider() {
		return userProvider;
	}
	public void setUserProvider(String userProvider) {
		this.userProvider = userProvider;
	}
	public String getUserExpiry() {
		return userExpiry;
	}
	public void setUserExpiry(String userExpiry) {
		this.userExpiry = userExpiry;
	}
	
	

}
