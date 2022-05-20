package com.naveen.jersey_db.user.repo;

import com.naveen.jersey_db.user.models.Role;
import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;

import java.util.*;

public class UserRepoFake {
    private static Map<Integer, User> DB = new HashMap<>();

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

        DB.put(user1.getId(), user1);
        DB.put(user2.getId(), user2);
    }

    public Users getAllUser() {
        Users users = new Users();
        users.setUsers(new ArrayList<>(DB.values()));
        return users;
    }

    public User getUserById(int id) {
        return DB.get(id);
    }

    public User createUser(User user) {
        DB.put(user.getId(), user);
        return DB.get(user.getId());
    }

    public User updateUser(int id, User user) {
        DB.put(id, user);
        return DB.get(id);
    }

    public void deleteUser(int id) {
        DB.remove(id);
    }
}
