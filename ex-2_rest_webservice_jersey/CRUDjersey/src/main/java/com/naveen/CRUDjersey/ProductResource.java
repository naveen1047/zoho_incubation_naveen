package com.naveen.CRUDjersey;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.naveen.dao.ProductDAO;
import com.naveen.domain.Product;
import com.naveen.ejb.ProductEJB;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Stateless
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	@EJB
	private ProductEJB productEJB;
	
	@GET
	public List<Product> getProducts() {
		return productEJB.getProducts();
	}
	
//	private ProductDAO dao = ProductDAO.getInstance();
//	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Product> list() {
//		return dao.getData();
//	}
//	
//	@GET
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response get(@PathParam("id") int id) {
//		Product product = dao.get(id);
//		
//		if (product != null) {
//			return Response.ok(product, MediaType.APPLICATION_JSON).build();
//		}
//		else {
//			return Response.status(Response.Status.NOT_FOUND).build();
//		}
//	}
//	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response add(Product product) throws URISyntaxException {
//		int newProductId = dao.add(product);
//		URI uri = new URI("products/" + newProductId);
//		return Response.created(uri).build();
//	}
//	
//	
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("{id}")
//	public Response update(@PathParam("id") int id, Product product) {
//	    product.setId(id);
//	    if (dao.update(product)) {
//	        return Response.ok().build();
//	    } else {
//	        return Response.notModified().build();
//	    }
//	}
//	
//	@DELETE
//	@Path("{id}")
//	public Response delete(@PathParam("id") int id) {
//	    if (dao.delete(id)) {
//	        return Response.ok().build();
//	    } else {
//	        return Response.notModified().build();
//	    }
//	}
}
