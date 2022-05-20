package com.naveen.jersey_db.user.service;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;
import com.naveen.jersey_db.user.repo.UserRepoFake;

public class UserServiceImplFake implements UserService {
    private final UserRepoFake userRepoFake;

    public UserServiceImplFake(UserRepoFake userRepoFake) {
        this.userRepoFake = userRepoFake;
    }

    @Override
    public Users getAllUsers() {
        return userRepoFake.getAllUser();
    }

    @Override
    public User getUserById(int id) {
        return userRepoFake.getAllUser()
                .getUsers()
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public User createUser(User user) {
        user.setId(userRepoFake.getAllUser().getUsers().size() + 1);
        user.setUri("/user-management/" + user.getId());
        System.out.println(user.getRoles());
        return userRepoFake.createUser(user);
    }

    @Override
    public User updateUser(int id, User user) {
        return userRepoFake.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        userRepoFake.deleteUser(id);
    }

//    @Override
//    public User getUserByUsername(String username) {
//        User user = userRepoFake.getAllUser()
//                .getUsers()
//                .stream()
//                .filter(u -> u.getName().equals(username))
//                .findFirst()
//                .get();
//
//        if (user.getId() <= 0)
//            return null;
//        return user;
//    }
}
