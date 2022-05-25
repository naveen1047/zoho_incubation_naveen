package com.naveen.jersey_db.orders;

public class DependenciesFactory {

    private static OrderService orderService;

    private static OrderRepo orderRepo;

    private static OrderRepo getOrderRepo() {
        if (orderRepo == null) orderRepo = new OrderRepo();
        return orderRepo;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderService(getOrderRepo());
        }
        return orderService;
    }
}
