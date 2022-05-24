package com.naveen.jersey_db.cart;

import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.user.filter.SecurityFilter;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

    CartServiceImpl service;

    public CartResource() {
        service = DependenciesFactory.getUserService();
    }

    @Path("/{id}")
    @GET
    @PermitAll
    public List<Product> getCart(@PathParam("id") int id) {
//        String name = SecurityFilter.getUser().getName();
//        System.out.println(name);
        return service.getCart(id);
    }

    @Path("/{id}")
    @PUT
    @PermitAll
    public void putCart(@PathParam("id") int id, @QueryParam("cartId") int cartId) {
         service.addCartItem(id, cartId);
    }

    @Path("/{id}")
    @DELETE
    @PermitAll
    public void deleteCart(@PathParam("id") int id) {
        service.deleteCart(id);
    }

    @Path("/all/{id}")
    @DELETE
    @PermitAll
    public void deleteCart(@PathParam("id") int id, @QueryParam("cartId") int cartId) {
        service.deleteCart(id, cartId);
    }
}
