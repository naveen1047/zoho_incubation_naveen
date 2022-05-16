package com.naveen.dao;

import java.util.ArrayList;
import java.util.List;
import com.naveen.domain.*;

public class ProductDAO {
	private static ProductDAO instance;
    private static List<Product> data = new ArrayList<>();
    
    static {
    	data.add(new Product(1, "Mac book",  1999.9f));
    	data.add(new Product(2, "Iphone",  799.9f));
    	data.add(new Product(3, "Ipad",  699.9f));
    	data.add(new Product(4, "Watch",  499.9f));
    }
  
    private ProductDAO() {
    	
    }
    
    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
         
        return instance;               
    }

	public static List<Product> getData() {
		return data;
	}

	public int add(Product product) {
        int newId = data.size() + 1;
        product.setId(newId);
        data.add(product);
         
        return newId;
    }
     
    public Product get(int id) {
    	return data.stream()
    			.filter(p -> p.getId() == id)
    			.findAny()
    			.orElse(null);
    }
     
    public boolean delete(int id) {
        Product productToFind = new Product(id);
        int index = data.indexOf(productToFind);
        if (index >= 0) {
            data.remove(index);
            return true;
        }
         
        return false;
    }
     
    public boolean update(Product product) {
    	int index = product.getId() - 1;
    	
        if (index >= 0) {
        	data.get(index).setName(product.getName());
        	data.get(index).setPrice(product.getPrice());
//            data.set(index, product);
            return true;
        }
        return false;
    }
    
}
