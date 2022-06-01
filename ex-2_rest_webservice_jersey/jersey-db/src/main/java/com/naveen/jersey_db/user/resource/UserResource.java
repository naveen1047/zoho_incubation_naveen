package com.naveen.jersey_db.user.resource;

import com.naveen.jersey_db.exception.CustomException;
import com.naveen.jersey_db.user.models.Role;
import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.util.DependenciesFactory;
import com.naveen.jersey_db.util.UserUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    UserService userService;

    public UserResource() {
        userService = DependenciesFactory.getUserService();
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @PermitAll
//    public Users getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GET
    @PermitAll
    public Users getAllUsersWithRoles() {
        return userService.getAllUsersWithRole();
    }

    @Path("/download")
    @GET
    @PermitAll
    public Users getAllUsersWithRolesCSV() {
        return userService.getAllUsersWithRoleCSV();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createUser(User user) throws URISyntaxException {
        if (user.getName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        /*if (userService.createUser(user) != null)*/
        userService.createUser(user);
            return Response.status(201).build();
//        return Response.status(500).entity("User didn't created").build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getUserById(@PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws URISyntaxException {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");

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
    @Path("/setrole/{id}")
    @PermitAll
    public Response setRolesById(@PathParam("id") int id, List<Role> roleList, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws URISyntaxException {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");

        User user = userService.getUserById(id);
        userService.setUserRolesById(id, roleList.stream().collect(Collectors.toSet()));
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
    @RolesAllowed("ADMIN")
    public Response updateUser(@PathParam("id") int id, User user, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws URISyntaxException {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");
        User temp = userService.getUserById(id);
        if (user == null) {
            return Response.status(404).build();
        }
        userService.updateUser(temp.getId(), user);
        return Response.status(200).entity(temp).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deleteUser(@PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) throws URISyntaxException {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id))) throw new CustomException("Id didn't match");
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
}
