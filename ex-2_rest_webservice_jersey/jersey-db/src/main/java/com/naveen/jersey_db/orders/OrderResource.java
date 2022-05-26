package com.naveen.jersey_db.orders;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    OrderService service;

    public OrderResource() {
        service = DependenciesFactory.getOrderService();
    }

    @Path("/{id}")
    @GET
    @PermitAll
    public List<Orders> getOrders(@PathParam("id") int id) {
        return service.getOrders(id);
    }

    @Path("/{id}")
    @POST
    public Response orderNow(@PathParam("id") int id) {
        service.orderNow(id);
        return Response.ok().build();
    }
}
