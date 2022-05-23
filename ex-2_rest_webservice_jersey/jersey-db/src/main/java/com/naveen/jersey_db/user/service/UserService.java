package com.naveen.jersey_db.user.service;

import com.naveen.jersey_db.user.models.Role;
import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;

import java.util.Set;

public interface UserService {
    Users getAllUsers();

    User getUserById(int id);

    User createUser(User user);

    User updateUser(int id, User user);

    void deleteUser(int id);

    Users getAllUsersWithRole();

    Users getAllUsersWithRoleCSV();


    User setUserRolesById(int id, Set<Role> roleSet);

//    User getUserByUsername(String username);
}
