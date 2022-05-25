package com.naveen.jersey_db.cart;

import com.naveen.jersey_db.exception.CustomException;
import com.naveen.jersey_db.product.models.Product;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    Connection con;

    public CartRepo() {
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

    public List<Product> getCart(int id) {
        String sql = "SELECT u.id as u_id, u.name as u_name, p.id as p_id, p.name as p_name, p.price\n" +
                "FROM users AS u\n" +
                "JOIN cart_product AS cp\n" +
                "\tON u.id = cp.cart_id\n" +
                "JOIN products AS p\n" +
                "\tON cp.product_id = p.id\n" +
                "WHERE u.id = ?";

        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt(3), rs.getString(4), rs.getDouble(5));

                products.add(p);
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }

        return products;
    }

    public Response addCartItem(int id, int cartId) {
        String sql = "INSERT INTO cart_product " +
                "VALUES (?, ?)";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            st.setInt(2, cartId);

            if (st.executeUpdate() == 0) {
                throw new CustomException("something went wrong");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Response.status(Response.Status.CREATED).build();
    }

    public void deleteCart(int id) {
        String sql = "DELETE FROM\n" +
                "cart_product\n" +
                "WHERE cart_id = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            if (st.executeUpdate() == 0) {
                throw new CustomException("something went wrong");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCart(int id, int cartId) {
        String sql = "DELETE FROM\n" +
                "cart_product\n" +
                "WHERE cart_id = ? AND product_id = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            st.setInt(2, cartId);

            if (st.executeUpdate() == 0) {
                throw new CustomException("something went wrong");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
