package com.naveen.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.naveen.domain.Product;

@Stateless
public class ProductEJB {
	@PersistenceContext(unitName = "productstore")
	private EntityManager entityManager;
	
	public Product getProduct(int id) {
		return entityManager.find(Product.class, id);
	}
	
	public List<Product> getProducts() {
		TypedQuery<Product> query = entityManager
				.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}
	
	// TODO: need to other impl
}
