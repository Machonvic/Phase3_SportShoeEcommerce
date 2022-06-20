package com.sportyshoe.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoe.model.Category;
import com.sportyshoe.model.Products;

@Repository
@Transactional
public class CategoryDAO {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	
    Timestamp ts2=Timestamp.valueOf(LocalDateTime.now());
    
	public int save(Category category) {
		// TODO Auto-generated method stub
	    String sql = "INSERT INTO products_category (CategoryName, CategoryDesc, CategoryCreated, CategoryModified) VALUES (?,?,?,?)";
		
		Object[] args = new Object[] {category.getCategoryName(),category.getCategoryDesc(),ts2,ts2};
		
		return jdbcTemplate.update(sql, args);
	}
	
	
	public List<Category> findAllCat() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from products_category",  new RowMapper<Category>() {

			
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category = new Category();
				category.setCategoryId(rs.getLong(1));
				category.setCategoryName(rs.getString(2));
				category.setCategoryDesc(rs.getString(3));
				return category;
			}} );
	}
	

}
