package com.naveen.jersey_db.user.util;

import com.naveen.jersey_db.user.repo.UserRepo;
import com.naveen.jersey_db.user.repo.UserRepoFake;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.service.UserServiceImpl;

public class DependenciesFactory {
    private static UserRepo userRepo;
    private static UserService userService;

    private static UserRepo getUserRepo() {
        if (userRepo == null)
            userRepo = new UserRepo();
        return userRepo;
    }

    public static UserService getUserService() {
        if (userService == null)
            userService = new UserServiceImpl(getUserRepo());
        return userService;
    }
}
