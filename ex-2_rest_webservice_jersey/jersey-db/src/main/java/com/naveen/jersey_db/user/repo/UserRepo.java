package com.naveen.jersey_db.user.repo;

import com.naveen.jersey_db.exception.CustomException;
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
//        user1.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.CUSTOMER)));

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

    public Users getAllUserAndRoles() {
        Users users = new Users();
        ArrayList<User> userList = new ArrayList<>();

        String sql = "SELECT u.id, u.password, u.name, u.url, r.name as role_name\n" +
                "FROM users as u\n" +
                "LEFT JOIN user_role as ur\n" +
                "   on u.id = ur.user_id\n" +
                "LEFT JOIN ROLES as r\n" +
                "   on ur.role_id = r.id";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                int id = rs.getInt(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
                String uri = rs.getString(4);

                Optional<User> u = userList
                        .stream()
                        .filter(user1 ->
                                user1.getId() == id
                        ).findFirst();

                user.setRoles(new HashSet<>());
                String roleName = rs.getString(5);

                if (!u.isPresent()) {
                    addUser(userList, user, id, password, name, uri, roleName);

                } else {
                    addRoleToExistingUser(userList, id, roleName);
                }
            }
            users.setUsers(userList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        return users;
    }

    private void addRoleToExistingUser(ArrayList<User> userList, int id, String roleName) {
        userList.stream()
                .filter(user1 -> user1.getId() == id)
                .forEach(user1 -> {
                    HashSet<Role> roles = (HashSet<Role>) user1.getRoles();
                    roles.add(new Role(roleName));
                    user1.setRoles(roles);
                });
    }

    private void addUser(ArrayList<User> userList,
                         User user, int id, String password,
                         String name, String uri, String roleName) {
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setUri(uri);

        if (roleName != null) {
            HashSet<Role> roles = (HashSet<Role>) user.getRoles();
            roles.add(new Role(roleName));
            user.setRoles(roles);
        }

        userList.add(user);
    }

    public User getUserById(int id) {
        ArrayList<User> userRolesList = new ArrayList<>();

        String sql = "SELECT u.id, u.password, u.name, u.url, r.name as role_name\n" +
                "FROM users as u\n" +
                "LEFT JOIN user_role as ur\n" +
                "   on u.id = ur.user_id\n" +
                "LEFT JOIN ROLES as r\n" +
                "   on ur.role_id = r.id\n" +
                "WHERE u.id = (?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            boolean isUserPresent = false;

            while (rs.next()) {
                isUserPresent = true;
                User user = new User();
                String password = rs.getString(2);
                String name = rs.getString(3);
                String uri = rs.getString(4);

                user.setRoles(new HashSet<>());
                String roleName = rs.getString(5);

                Optional<User> u = userRolesList
                        .stream()
                        .filter(user1 ->
                                user1.getId() == id
                        ).findFirst();

                user.setRoles(new HashSet<>());

                if (!u.isPresent()) {
                    addUser(userRolesList, user, id, password, name, uri, roleName);
                } else {
                    addRoleToExistingUser(userRolesList, id, roleName);
                }
            }
            if (!isUserPresent) {
                throw new UserNotFoundException(id + " not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }

        return userRolesList.get(0);
    }

    public User createUser(User user) {
        String sql = "INSERT INTO users\n" +
                "(password, name, url)\n" +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getPassword());
            st.setString(2, user.getName());
            st.setString(3, user.getUri());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        return getUserByUsernamePassword(user.getName(), user.getPassword());
    }

    private User getUserByUsernamePassword(String name, String password) {
        User user;
        String sql = "SELECT * FROM users\n" +
                "WHERE name = (?) AND password = (?)";

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
            e.printStackTrace();
            throw new CustomException(e.getMessage()
            );
        }

        return user;
    }

    public User updateUser(int id, User user) {
        User currentUser = getUserById(id);
        String sql = "UPDATE users " +
                "SET name = ?, url = ? " +
                "WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.println(currentUser.getName());
            System.out.println(currentUser.getUri());

            ps.setString(1, user.getName() == null ? currentUser.getName() : user.getName());
            ps.setString(2, user.getUri() == null ? currentUser.getUri() : user.getUri());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return getUserById(id);
    }

    public void deleteUser(int id) {
        String sql = "DELETE "
                + "FROM users "
                + "WHERE id = (?)";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            if (st.executeUpdate() == 0) {
                throw new CustomException("id not found");
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public User setUserRolesById(int id, Set<Role> roleSet) {
        roleSet.stream()
                .forEach(
                        role -> {
                            String sql = "insert into user_role\n" +
                                    "values(?, ?)";

                            int roleId = getRoleIdByName(role.getName());

                            try {
                                PreparedStatement st = con.prepareStatement(sql);
                                st.setInt(1, id);

                                st.setInt(2, roleId);
                                // updating role
                                if (st.executeUpdate() == 0) {
                                    throw new CustomException("id not found");
                                }

                            } catch (Exception e) {
                                throw new CustomException(e.getMessage() + " or " + " updated ");
                            }
                        }
                );

        return getUserById(id);
    }

    private int getRoleIdByName(String name) {
        String sql = "select id from roles\n" +
                "where name = (?)";

        int id = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            System.out.println("asdfas");
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new UserNotFoundException(id + " not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }

        System.out.println(id);
        return id;
    }
}
