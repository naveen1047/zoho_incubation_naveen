package com.naveen.jersey_db.user.service;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;
import com.naveen.jersey_db.user.repo.UserRepo;

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Users getAllUsers() {
        return userRepo.getAllUser();
    }

    @Override
    public User getUserById(int id) {
        return userRepo.getAllUser()
                .getUsers()
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public User createUser(User user) {
        user.setId(userRepo.getAllUser().getUsers().size() + 1);
        user.setUri("/user-management/" + user.getId());
        return userRepo.createUser(user);
    }

    @Override
    public User updateUser(int id, User user) {
        return userRepo.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteUser(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getAllUser()
                .getUsers()
                .stream()
                .filter(u -> u.getName().equals(username))
                .findFirst()
                .get();
    }
}
