package com.naveen.jersey_db.cart;

public class DependenciesFactory {
    private static CartRepo cartRepo;
    private static CartServiceImpl cartService;

    private static CartRepo getCartRepo() {
        if (cartRepo == null)
            cartRepo = new CartRepo();
        return cartRepo;
    }

    public static CartServiceImpl getCartService() {
        if (cartService == null)
            cartService = new CartServiceImpl(getCartRepo());
        return cartService;
    }
}
