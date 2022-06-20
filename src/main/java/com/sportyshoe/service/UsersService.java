package com.sportyshoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoe.DAO.UsersDAO;
import com.sportyshoe.model.Users;



@Service
public class UsersService {
	
	@Autowired
	 private UsersDAO usersDAO;

	
    public List<Users> getUsers(String keyword){
		
		return usersDAO.findAllUsers(keyword);		
			}
    
    public Users getUser(String keyword){
		
 		return usersDAO.getUsersByUserName(keyword);		
 			}
    
    public Users getAdmin(String keyword){
		
 		return usersDAO.getAdminsByUserName(keyword);		
 			}

}
