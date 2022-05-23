package com.naveen.jersey_db.user.service;


import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;
import com.naveen.jersey_db.user.repo.UserRepo;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Users getAllUsersWithRoleCSV() {
        Users users = userRepo.getAllUserAndRoles();

        final String path = "C:\\Users\\LENOVO\\OneDrive\\Desktop\\zoho incu\\" +
                "zoho exercise\\" +
                "ex-2_rest_webservice_jersey\\jersey-db\\downloads\\users.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(users.getUsers().stream().map(
                            value -> {
                                return new String[]
                                        {Integer.toString(value.getId()),
                                                value.getName(),
                                                value.getRoles().stream().map(v -> v.getName()).collect(Collectors.toList()).toString(),
                                                value.getUri(),
                                                value.getPassword()

                                        };
                            }
                    )
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return users;
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

