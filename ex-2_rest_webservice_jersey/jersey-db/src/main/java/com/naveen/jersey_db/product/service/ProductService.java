package com.naveen.jersey_db.product.service;

import com.naveen.jersey_db.product.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    List<Product> getProductsCSV();
    Product getProduct(int id);

    Product addProduct(Product product);

    Product editProduct(Product product);

    void deleteProduct(int id);
}
