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
        System.out.println("all users");
        return userRepo.getAllUser();
    }

    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }

    @Override
    public User createUser(User user) {
        user.setUri("/user-management/" + user.getId());
        System.out.println(user.getRoles());
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
    public Users getAllUsersWithRole() {
        return userRepo.getAllUserAndRoles();
    }

//    @Override
//    public User getUserByUsername(String id) {
//        User user = userRepo.getAllUser()
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

