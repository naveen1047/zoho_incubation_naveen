package com.naveen.jersey_db.wallet;

import com.naveen.jersey_db.exception.CustomException;

import java.sql.*;

public class WalletRepo {
    Connection con;

    public WalletRepo() {
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


    public double getBalance(int id) {
        String sql = "SELECT balance\n" +
                "FROM wallet\n" +
                "WHERE id = ?";

        double balance = 0.0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) balance = rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return balance;
    }

    public Wallet setBalance(int id, double balance) {
        String sql = "SELECT balance\n" +
                "FROM wallet\n" +
                "WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                // already exists then update
                sql = "INSERT INTO wallet VALUES (?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setDouble(2, balance);
            } else {
                // not exists then insert
                sql = "UPDATE wallet\n" +
                        "SET balance = ?\n" +
                        "WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setDouble(1, balance);
                ps.setInt(2, id);
            }
             ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Wallet(id, getBalance(id));
    }
}
