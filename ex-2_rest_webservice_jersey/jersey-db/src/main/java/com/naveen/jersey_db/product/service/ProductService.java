package com.naveen.jersey_db.product.service;

import com.naveen.jersey_db.product.models.Product;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    List<Product> getProductsCSV();
    Product getProduct(int id);

    Response addProduct(Product product);

    Product editProduct(Product product);

    void deleteProduct(int id);
}
