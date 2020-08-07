package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.entity.Product;

public class ProductDao {
	
	   public Product select(int id) {
		   Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				
				conn = ConnManager.connect();
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				//String url = "jdbc:oracle:thin:@localhost:1521:ORCL"; // ORCLDB.localdomain or OSE or //32118:ORCL
				//String user = "system";
				//String pass = "oracle"; //Oracle18
				//conn = DriverManager.getConnection(url, user, pass);
				System.out.println("connection rocks ");
				String sql = "SELECT * FROM tbl_product WHERE id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
			
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					return product;
					
			
				}
			
			return null;
			}
			
			catch( SQLException e) {
				e.printStackTrace(); //we throw user define error
			return null;
			}
			finally {
				try { conn.close(); } catch(Exception e) { }
			}
			
		   
	   }

	   public List<Product>selectAll(){
		   Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnManager.connect();
				//System.out.println("connection suces");
				String sql = "SELECT * FROM tbl_product";
				stmt = conn.prepareStatement(sql);
				//stmt.setInt(1, id);
			
				rs = stmt.executeQuery();
				List<Product> list = new ArrayList<>();
				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					//return product;
					
				list.add(product);
				}
				return list;
				//return null;
			}
			catch(SQLException e) {
				e.printStackTrace(); //we throw user define error
			return null;
			}
			finally {
				try { conn.close(); } catch(Exception e) { }
			}
			    
	   }  
	   public void insert(Product product) {
		   
		   
		   Connection conn = null;
			PreparedStatement stmt = null;
			//ResultSet rs = null;
			try {
				conn = ConnManager.connect();
				System.out.println("connection suces");
				String sql = "INSERT INTO tbl_product VALUES(?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, product.getId());
				stmt.setString(2,  product.getName());
				stmt.setDouble(3, product.getPrice());
			int count = stmt.executeUpdate();
				System.out.println(count);
			
				
			}
			catch( SQLException e) {
				e.printStackTrace(); //we throw user define error
			
			}
			finally {
				try { conn.close(); } catch(Exception e) { }
			}
			
		   
	   }
		   
	   }

