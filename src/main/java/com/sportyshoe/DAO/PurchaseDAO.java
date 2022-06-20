package com.sportyshoe.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoe.model.PurchaseDetails;

@Repository
@Transactional
public class PurchaseDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<PurchaseDetails> findAllPurchaseDetails(long l,String purchaseDate) {
		// TODO Auto-generated method stub
		String category;
		String date;
		
			
		
		if(l==0) {
			category="%";
		}
		else {
			category="%"+l;
		}
		
		if( purchaseDate.equals("0")) {
			date="%";
		}
		else {
			date="%"+purchaseDate;
		}
		
		//System.out.println("From DAO    "+date);
		
		//date="%";
		
	    String sql1="select  u.UserName, u.UserFirstName,u.UserLastName,p.productname, s.category_id, c.categoryname, s.product_amount, \r\n"
	    		+ " s.Product_payment, s.Product_quantity, s.purchase_date \r\n"
	    		+ " from products p, products_category c ,purchase_product s ,users u\r\n"
	    		+ " where p.ProductCategoryID=c.CategoryID \r\n"
	    		+ " and p.productid=s.product_id \r\n"
	    		+ " and p.ProductCategoryID=s.category_id\r\n"
	    		+ " and u.user_id=s.customer_id and s.category_id like ? and date(s.purchase_date) like ?";
			
		Object[] param= new Object[] {category,date};
		
	
		return jdbcTemplate.query(sql1, param,new RowMapper<PurchaseDetails>() {

			
			public PurchaseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				PurchaseDetails purchaseDetails = new PurchaseDetails();
				purchaseDetails.setUserName(rs.getString("u.UserName"));
				purchaseDetails.setUserFirstname(rs.getString("u.UserFirstName"));
				purchaseDetails.setUserLastname(rs.getString("u.UserLastName"));
				purchaseDetails.setProductName(rs.getString("p.productname"));
				purchaseDetails.setCategoryId(rs.getLong("s.category_id"));
				purchaseDetails.setCategoryName(rs.getString("c.categoryname"));
				purchaseDetails.setProductAmount(rs.getBigDecimal("s.product_amount"));
				purchaseDetails.setProductPayment(rs.getBigDecimal("s.Product_payment"));
				purchaseDetails.setProductQuantity(rs.getLong("s.Product_quantity"));
				purchaseDetails.setPurchaseDate(rs.getDate("s.purchase_date"));
				return purchaseDetails;
			}} );
	}
}
