package com.naveen.jersey_db.product.resourse;

import java.util.*;

import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.product.service.ProductService;
import com.naveen.jersey_db.product.util.DependenciesFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("products")
public class ProductResource {
    private ProductService service;

    public ProductResource() {
        service = DependenciesFactory.getProductService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") int id) {
        return service.getProduct(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setProduct(Product p) {
        service.addProduct(p);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProduct(Product p) {
        service.editProduct(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) {
        service.deleteProduct(id);
        return Response.ok().build();
    }
}
