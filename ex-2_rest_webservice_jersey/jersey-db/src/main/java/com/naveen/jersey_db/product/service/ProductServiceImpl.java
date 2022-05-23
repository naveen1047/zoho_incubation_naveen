package com.naveen.jersey_db.product.service;

import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.product.repo.ProductRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Product> getProductsCSV() {

        final String path = "C:\\Users\\LENOVO\\OneDrive\\Desktop\\zoho incu\\" +
                "zoho exercise\\" +
                "ex-2_rest_webservice_jersey\\jersey-db\\downloads\\products.csv";

        List<Product> products = repository.getProducts();

        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(products.stream().map(
                            value -> {
                                return new String[]
                                        {Integer.toString(value.getId()),
                                                value.getName(),
                                                Double.toString(value.getPrice())};
                            }
                    )
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return products;
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
