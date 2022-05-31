package com.naveen.jersey_db.cart;

import com.naveen.jersey_db.exception.CustomException;
import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.util.UserUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

    CartServiceImpl service;

    public CartResource() {
        service = DependenciesFactory.getCartService();

    }

    @Path("/{id}")
    @GET
    @PermitAll
    public List<Product> getCart(@PathParam("id") int id,
                                 @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");

        return service.getCart(id);
    }

    @Path("/{id}")
    @PUT
    @PermitAll
    public Response putCart(@PathParam("id") int id,
                            @QueryParam("cartId") int cartId,
                            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");
         return service.addCartItem(id, cartId);
    }

    @Path("/{id}")
    @DELETE
    @PermitAll
    public void deleteCart(@PathParam("id") int id,
                           @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");
        service.deleteCart(id);
    }

    @Path("/all/{id}")
    @DELETE
    @RolesAllowed("CUSTOMER")
    public void deleteCart(@PathParam("id") int id,
                           @QueryParam("cartId") int cartId,
                           @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");

        service.deleteCart(id, cartId);
    }
}
