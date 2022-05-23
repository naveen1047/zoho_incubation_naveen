package com.naveen.jersey_db.product.resource;

import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.product.service.ProductService;
import com.naveen.jersey_db.product.util.DependenciesFactory;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private ProductService service;

    public ProductResource() {
        service = DependenciesFactory.getProductService();
    }

    @GET
    @PermitAll
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @Path("/download")
    @GET
    @PermitAll
    public List<Product> getProductsCSV() {
        return service.getProductsCSV();
    }


    @GET
    @Path("{id}")
    @PermitAll
    public Product getProduct(@PathParam("id") int id) {
        return service.getProduct(id);
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response setProduct(Product p) {
        return service.addProduct(p);

    }

    @PUT
    @RolesAllowed("ADMIN")
    public Product editProduct(Product p) {
        return service.editProduct(p);

    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("ADMIN")
    public void deleteProduct(@PathParam("id") int id) {
        service.deleteProduct(id);

    }
}
