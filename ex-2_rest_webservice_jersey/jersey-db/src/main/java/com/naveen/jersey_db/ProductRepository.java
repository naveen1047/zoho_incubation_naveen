package com.naveen.jersey_db;

import java.sql.*;
import java.util.*;

public class ProductRepository {

	Connection con = null;
	
	public ProductRepository() {

		
		String url = "jdbc:mysql://localhost:3306/ecommerce";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		
		String sql = "SELECT * FROM products";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Product p = new Product(
						rs.getInt(1), rs.getString(2), rs.getDouble(3));
				
				products.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return products;
	}
	
	public Product getProduct(int id) {
		Product p = new Product();
		String sql = "SELECT * FROM products where id = (?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				 p = new Product(
						rs.getInt(1), rs.getString(2), rs.getDouble(3));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return p;
	}
	
	public void addProduct(Product p) {
		String sql = "insert into products value (?, ?, ?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, p.getId());
			st.setString(2, p.getName());
			st.setDouble(3, p.getPrice());
			st.executeUpdate();

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void editProduct(Product p) {
		String sql = "UPDATE products "
				+ "SET name = ?, price = ? "
				+ "WHERE id = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, p.getName());
			st.setDouble(2, p.getPrice());
			st.setInt(3, p.getId());
			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public void delete(int id) {
		String sql = "DELETE "
				+ "FROM products "
				+ "WHERE id = " + id;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}
}
