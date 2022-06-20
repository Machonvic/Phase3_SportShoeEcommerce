package com.sportyshoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sportyshoe.DAO.AppUserDAO;
import com.sportyshoe.model.AppUser;

@Service
public class ChangePasswordService  {
	
	AppUser user;
	

	 @Autowired
	 private AppUserDAO appUserDAO;
	
	@Autowired
	UserDetailsServiceIm userDetailsService;
	
	public String changePassword(String userName, String newPassword){
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
		
		if(userDetails!=null)
		{
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		        String encodedPassword = passwordEncoder.encode(newPassword);
		        //customer.setPassword(encodedPassword);
		        appUserDAO.updatePassword(userName, encodedPassword);
			
		        return encodedPassword;
		}
		
		
		
		return userName;
	}
		
	

}
