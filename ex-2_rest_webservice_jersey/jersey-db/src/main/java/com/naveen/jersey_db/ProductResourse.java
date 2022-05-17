package com.naveen.jersey_db;

import java.util.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("products")
public class ProductResourse {
	ProductRepository repo = new ProductRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts() {
		return repo.getProducts();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam("id") int id) {
		return repo.getProduct(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setProduct(Product p) {
		repo.addProduct(p);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editProduct(Product p) {
		repo.editProduct(p);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("id") int id) {
		repo.delete(id);
		return Response.ok().build();
	}
}
