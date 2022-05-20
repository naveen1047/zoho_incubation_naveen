package com.naveen.jersey_db.user.repo;

import com.naveen.jersey_db.product.exception.CustomException;
import com.naveen.jersey_db.user.exception.UserNotFoundException;
import com.naveen.jersey_db.user.models.Role;
import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;

import java.sql.*;
import java.util.*;

public class UserRepo {

    Connection con;

    public UserRepo() {

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


    static {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John");
        user1.setUri("/user-management/1");
        user1.setPassword("John");
        user1.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.CUSTOMER)));

        User user2 = new User();
        user2.setId(2);
        user2.setName("Harry");
        user2.setPassword("Harry");
        user2.setUri("/user-management/2");

    }

    public Users getAllUser() {
        Users users = new Users();
        ArrayList<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(1));
                user.setPassword(rs.getString(2));
                user.setName(rs.getString(3));
                user.setUri(rs.getString(4));

                userList.add(user);
            }
            users.setUsers(userList);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return users;
    }

    public User getUserById(int id) {
        User user = new User();
        String sql = "SELECT * FROM users " +
                "WHERE id = (?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String password = rs.getString(1);
                String name = rs.getString(2);
                String url = rs.getString(3);

                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                user.setUri(url);
            } else {
                throw new UserNotFoundException(id + " not found.");
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage()
            );
        }

        return user;
    }

    public User createUser(User user) {
        String sql = "INSERT INTO users " +
                "(password, name, uri)" +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getPassword());
            st.setString(2, user.getName());
            st.setString(3, user.getUri());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("erroe");
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
        return getUserByUsernamePassword(user.getName(), user.getPassword());
    }

    private User getUserByUsernamePassword(String name, String password) {
        User user;
        String sql = "SELECT * FROM users" +
                "WHERE username = (?) AND password = (?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
            } else {
                throw new UserNotFoundException(name + " not found.");
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage()
            );
        }

        return user;
    }

    public User updateUser(int id, User user) {
        String sql = "UPDATE users " +
                "SET name = ?, password = ?, url = ? " +
                "WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUri());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return getUserByUsernamePassword(user.getName(), user.getPassword());
    }

    public void deleteUser(int id) {
        String sql = "DELETE "
                + "FROM users "
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
