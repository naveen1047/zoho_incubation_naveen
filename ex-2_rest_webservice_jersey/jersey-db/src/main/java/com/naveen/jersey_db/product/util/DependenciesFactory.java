package com.naveen.jersey_db.product.util;

import com.naveen.jersey_db.product.repo.ProductRepository;
import com.naveen.jersey_db.product.service.ProductService;
import com.naveen.jersey_db.product.service.ProductServiceImpl;

public class DependenciesFactory {
    private static ProductRepository productRepository;
    private static ProductService productService;

    private static ProductRepository getProductRepo() {
        if (productRepository == null)
            productRepository = new ProductRepository();
        return  productRepository;
    }

    public static ProductService getProductService() {
        if (productService == null)
            productService = new ProductServiceImpl(getProductRepo());
        return productService;
    }
}
