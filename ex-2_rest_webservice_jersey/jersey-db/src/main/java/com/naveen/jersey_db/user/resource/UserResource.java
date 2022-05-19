package com.naveen.jersey_db.user.resource;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.util.DependenciesFactory;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("users")
public class UserResource {
    UserService userService;

    public UserResource() {
        userService = DependenciesFactory.getProductService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Users getAllUsers() {
        return userService.getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createUser(User user) throws URISyntaxException {
        if (user.getName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        if (userService.createUser(user) != null)
            return Response.status(201).contentLocation(new URI(user.getUri())).build();
        return Response.status(500).entity("User didn't created").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getUserById(@PathParam("id") int id) throws URISyntaxException {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response updateUser(@PathParam("id") int id, User user) throws URISyntaxException {
        User temp = userService.getUserById(id);
        if (user == null) {
            return Response.status(404).build();
        }
        temp.setName(user.getName());
        userService.updateUser(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    @PermitAll
    public Response deleteUser(@PathParam("id") int id) throws URISyntaxException {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
}
