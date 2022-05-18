package com.naveen.jersey_db.product.resourse;

import java.util.*;

import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.product.service.ProductService;
import com.naveen.jersey_db.product.util.DependenciesFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    private ProductService service;

    public ProductResource() {
        service = DependenciesFactory.getProductService();
    }

    @GET
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GET
    @Path("{id}")
    public Product getProduct(@PathParam("id") int id) {
        return service.getProduct(id);
    }

    @POST
    public Response setProduct(Product p) {
        service.addProduct(p);
        return Response.ok().build();
    }

    @PUT
    public Response editProduct(Product p) {
        service.editProduct(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        service.deleteProduct(id);
        return Response.ok().build();
    }
}
