package com.sportyshoe.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoe.model.Category;
import com.sportyshoe.model.Products;
import com.sportyshoe.model.Users;

@Repository
@Transactional
public class UsersDAO {
	
Timestamp ts2=Timestamp.valueOf(LocalDateTime.now());
    
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Users> findAllUsers(String keyword) {
		// TODO Auto-generated method stub
		keyword=keyword +"%";
		String sql1="select u.UserName, u.UserEmail, u.UserFirstName,u.UserLastName from app_user a, users u where a.USER_ID=u.user_id and u.userfirstname like ?";
		
		Object[] param= new Object[] {keyword};
		
		return jdbcTemplate.query(sql1, param ,new RowMapper<Users>() {

			
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users users = new Users();
				users.setUserName(rs.getString("u.UserName"));
				users.setUserEmail(rs.getString("u.UserEmail"));
				users.setUserFirstname(rs.getString("u.UserFirstName"));
				users.setUserLastname(rs.getString("u.UserLastName"));
				return users;
			}} );
	}
	
	public List<Users> findAdmin(String keyword) {
		// TODO Auto-generated method stub
	    String sql1="  select u.AdminUserName, u.AdminEmail, u.AdminFirstName,u.AdminLastName from app_user a, admins u where a.USER_ID=u.adminuserid and a.user_name= ?";
			
		Object[] param= new Object[] {keyword};
		
	
		return jdbcTemplate.query(sql1, param ,new RowMapper<Users>() {

			
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users users = new Users();
				users.setUserName(rs.getString("u.AdminUserName"));
				users.setUserEmail(rs.getString("u.AdminEmail"));
				users.setUserFirstname(rs.getString("u.AdminFirstName"));
				users.setUserLastname(rs.getString("u.AdminLastName"));
				return users;
			}} );
	}
	
	public List<Users> findUser(String keyword) {
		// TODO Auto-generated method stub
		String sql1="select u.UserName, u.UserEmail, u.UserFirstName,u.UserLastName from app_user a, users u where a.USER_ID=u.user_id and a.user_name= ?";
			
		Object[] param= new Object[] {keyword};
		
	
		return jdbcTemplate.query(sql1, param ,new RowMapper<Users>() {

			
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users users = new Users();
				users.setUserName(rs.getString("u.UserName"));
				users.setUserEmail(rs.getString("u.UserEmail"));
				users.setUserFirstname(rs.getString("u.UserFirstName"));
				users.setUserLastname(rs.getString("u.UserLastName"));
				return users;
			}} );
	}
	
	
	public Users getUsersByUserName(String keyword) {
		// TODO Auto-generated method stub
		
		 String sql="select u.UserName, u.UserEmail, u.UserFirstName,u.UserLastName from app_user a, users u where a.USER_ID=u.user_id and a.user_name= ?";
  
		    return jdbcTemplate.queryForObject(sql, new Object[]{keyword},new BeanPropertyRowMapper<Users>(Users.class));    
		//return null;
	}
	
	public Users getAdminsByUserName(String keyword) {
		// TODO Auto-generated method stub
		
	    String sql="select u.AdminUserName, u.AdminEmail, u.AdminFirstName,u.AdminLastName from app_user a, admins u where a.USER_ID=u.adminuserid and a.user_name like ?";
 
		    return jdbcTemplate.queryForObject(sql, new Object[]{keyword},new BeanPropertyRowMapper<Users>(Users.class));    
		//return null;
	}
	
	

}
