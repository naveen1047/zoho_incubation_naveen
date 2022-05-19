package com.naveen.jersey_db.user.util;

import com.naveen.jersey_db.user.repo.UserRepoFake;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.service.UserServiceImpl;

public class DependenciesFactory {
    private static UserRepoFake userRepoFake;
    private static UserService userService;

    private static UserRepoFake getUserRepo() {
        if (userRepoFake == null)
            userRepoFake = new UserRepoFake();
        return userRepoFake;
    }

    public static UserService getProductService() {
        if (userService == null)
            userService = new UserServiceImpl(getUserRepo());
        return userService;
    }
}
