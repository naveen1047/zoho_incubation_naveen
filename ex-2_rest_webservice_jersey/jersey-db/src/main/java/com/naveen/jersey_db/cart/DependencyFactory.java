package com.naveen.jersey_db.cart;


public class DependencyFactory {
    private static CartRepo cartRepo;
    private static CartService cartService;

    private static CartRepo getProductRepo() {
        if (cartRepo == null)
            cartRepo = new CartRepo();
        return  cartRepo;
    }

    public static CartService getCartService() {
        if (cartService == null)
            cartService = new CartServiceImpl(getProductRepo());
        return cartService;
    }
}