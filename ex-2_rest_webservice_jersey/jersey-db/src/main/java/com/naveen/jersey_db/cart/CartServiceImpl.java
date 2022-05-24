package com.naveen.jersey_db.cart;


import com.naveen.jersey_db.product.models.Product;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartRepo repo;

    CartServiceImpl(CartRepo cartRepo) {
        this.repo = cartRepo;
    }

    @Override
    public List<Product> getCart(int id) {
        return repo.getCart(id);
    }

    @Override
    public Response addCartItem(int id, int cartId) {
        return repo.addCartItem(id, cartId);
    }

    @Override
    public void deleteCart(int id, int cartId) {
        repo.deleteCart(id, cartId);
    }

    @Override
    public void deleteCart(int id) {
        repo.deleteCart(id);
    }
}
