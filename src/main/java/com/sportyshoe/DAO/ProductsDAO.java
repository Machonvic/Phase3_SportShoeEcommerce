package com.sportyshoe.DAO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.sportyshoe.model.Products;

@Repository
@Transactional
public class ProductsDAO implements ProductsRepository {
	
	Date date = new Date(0);  
    Timestamp ts=new Timestamp(date.getTime());
    Timestamp ts2=Timestamp.valueOf(LocalDateTime.now());
    
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		// TODO Auto-generated method stub
		
		return jdbcTemplate
                .queryForObject("select count(*) from products", Integer.class);
		
	}

	@Override
	public int save(Products product) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
                "insert into products (ProductName, ProductDesc,ProductCategoryID,ProductPrice,ProductCreated,ProductModified,ProductImage) values(?,?,?,?,?,?,?)",
                product.getProductName(), product.getProductDesc(),product.getProductCategoryID(),product.getProductPrice(),ts2,ts2,"shoes");
	}

	
	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM products WHERE ProductID = ?";
		
		Object[] args = new Object[] {id};
		
		return jdbcTemplate.update(sql,args);
	}

	@Override
	public List<Products> findAll() {
		// TODO Auto-generated method stub
		
		String sql="select productid, productname, productdesc,(select categoryname from products_category where categoryid=productcategoryid) Category, productprice from products";
		String sql2="select * from products";
		
		return jdbcTemplate.query(sql,  new RowMapper<Products>() {

			@Override
			public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
				Products product = new Products();
				product.setProductId(rs.getLong(1));
				product.setProductName(rs.getString(2));
				product.setProductDesc(rs.getString(3));
				product.setProductCategoryID(rs.getString(4));
				product.setProductPrice(rs.getBigDecimal(5));
				return product;
			}} );
	}
	
	
	@Override
	public List<Products> findByNameAndPrice(String name, BigDecimal price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Products> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Products getProductById(long id) {
		// TODO Auto-generated method stub
		
		 String sql="select productid, productname, productdesc,(select categoryname from products_category where categoryid=productcategoryid) Category, productprice from products where productid=?";
			
		 String sql2="SELECT * from PRODUCTS where productid=?";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Products>(Products.class));    
		//return null;
	}

	@Override
	public void updateProduct(Products product) {
		// TODO Auto-generated method stub
		  String sql2 = "UPDATE products SET  productname = ? ,productdesc = ?, productcategoryid = ?, productprice = ? , productmodified = ?  WHERE ProductID = ?";
		  Object[] args = new Object[] { product.getProductName(), product.getProductDesc(),product.getProductCategoryID(),product.getProductPrice(),ts2, product.getProductId()};
		  jdbcTemplate.update(sql2 ,args);		
	}
	
}
