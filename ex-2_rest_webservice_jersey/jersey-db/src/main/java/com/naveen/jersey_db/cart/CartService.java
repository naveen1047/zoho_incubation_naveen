package com.naveen.jersey_db.cart;

import com.naveen.jersey_db.product.models.Product;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface CartService {
    List<Product> getCart(int id);

    Response addCartItem(int id, int cartId);

    void deleteCart(int id, int cartId);

    void deleteCart(int id);

}
