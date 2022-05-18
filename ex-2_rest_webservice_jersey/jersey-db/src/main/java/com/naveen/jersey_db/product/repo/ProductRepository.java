package com.naveen.jersey_db.product.repo;

import com.naveen.jersey_db.product.exception.CustomException;
import com.naveen.jersey_db.product.exception.ProductNotFoundException;
import com.naveen.jersey_db.product.models.Product;

import java.sql.*;
import java.util.*;

public class ProductRepository {

    Connection con;

    public ProductRepository() {

        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
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
            throw new CustomException(e.getMessage());
        }

        return products;
    }

    public Product getProduct(int id) {
        Product p;
        String sql = "SELECT * FROM products where id = (?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Product(
                        rs.getInt(1), rs.getString(2), rs.getDouble(3));
            } else {
                throw new ProductNotFoundException(id + " not found.");
            }

        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }
        return p;
    }

    public Product addProduct(Product p) {
        String sql = "insert into products value (?, ?, ?)";

        try {
            if (p.getId() <= 0) {
                throw new CustomException("id should not be empty");
            }
            if (p.getName() == null) {
                throw new CustomException("name should not be empty");

            }
            if (p.getPrice() <= 0.0) {
                throw new CustomException("price should not less than or equals to zero");
            }
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, p.getId());
            st.setString(2, p.getName());
            st.setDouble(3, p.getPrice());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    public Product editProduct(Product p) {
        if (p.getId() <= 0) {
            throw new CustomException("id should not be empty");
        }
        if (p.getName() == null) {
            throw new CustomException("name should not be empty");

        }
        if (p.getPrice() <= 0.0) {
            throw new CustomException("price should not less than or equals to zero");
        }

        String sql = "UPDATE products "
                + "SET name = ?, price = ? "
                + "WHERE id = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, p.getName());
            st.setDouble(2, p.getPrice());
            st.setInt(3, p.getId());
            if (st.executeUpdate() == 0) {
                throw new CustomException("id incorrect ");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CustomException(e.getMessage());
        }
        return p;
    }

    public void delete(int id) {
        String sql = "DELETE "
                + "FROM products "
                + "WHERE id = " + id;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            if (st.executeUpdate() == 0) {
                throw new CustomException("id not found");
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }
}
