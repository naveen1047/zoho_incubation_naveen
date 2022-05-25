package com.naveen.jersey_db.orders;

import java.util.List;

interface OrderServiceInterface {
    List<Orders> getOrders(int id);
    void orderNow(int id);
}

public class OrderService implements OrderServiceInterface {
    OrderRepo repo;

    public OrderService(OrderRepo orderRepo) {
        this.repo = orderRepo;
    }
    @Override
    public List<Orders> getOrders(int id) {
        return repo.getOrders(id);
    }

    @Override
    public void orderNow(int id) {
        repo.orderNow(id);
    }
}
