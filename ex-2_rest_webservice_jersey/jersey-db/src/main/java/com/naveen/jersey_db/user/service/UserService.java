package com.naveen.jersey_db.user.service;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;

public interface UserService {
    Users getAllUsers();

    User getUserById(int id);

    User createUser(User user);

    User updateUser(int id, User user);

    void deleteUser(int id);

    Users getAllUsersWithRole();

    Users getAllUsersWithRoleCSV();

//    User getUserByUsername(String username);
}
