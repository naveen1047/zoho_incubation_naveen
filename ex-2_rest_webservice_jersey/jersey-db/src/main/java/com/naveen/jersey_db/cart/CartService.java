package com.naveen.jersey_db.cart;

import com.naveen.jersey_db.product.models.Product;

import java.util.List;

public interface CartService {
    List<Product> getCart(int id);

    void addCartItem(int id, int cartId);

    void deleteCart(int id, int cartId);

    void deleteCart(int id);

}
