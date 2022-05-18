package com.naveen.jersey_db.product.service;

import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.product.repo.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return repository.getProducts();
    }

    @Override
    public Product getProduct(int id) {
        return repository.getProduct(id);
    }

    @Override
    public Product addProduct(Product product) {
        return repository.addProduct(product);
    }

    @Override
    public Product editProduct(Product product) {
        return repository.editProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        repository.delete(id);
    }
}
