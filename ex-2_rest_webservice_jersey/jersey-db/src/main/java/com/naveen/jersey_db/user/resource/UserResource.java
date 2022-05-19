package com.naveen.jersey_db.user.resource;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.models.Users;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("users")
public class UserResource {
    private static Map<Integer, User> DB = new HashMap<>();

    static {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John");
        user1.setUri("/user-management/1");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Harry");
        user2.setUri("/user-management/2");

        DB.put(user1.getId(), user1);
        DB.put(user2.getId(), user2);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Users getAllUsers() {
        Users users = new Users();
        users.setUsers(new ArrayList<>(DB.values()));
        return users;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createUser(User user) throws URISyntaxException
    {
        if(user.getName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        user.setId(DB.values().size()+1);
        user.setUri("/user-management/"+user.getId());
        DB.put(user.getId(), user);
        return Response.status(201).contentLocation(new URI(user.getUri())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getUserById(@PathParam("id") int id) throws URISyntaxException
    {
        User user = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/"+id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response updateUser(@PathParam("id") int id, User user) throws URISyntaxException
    {
        User temp = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        temp.setName(user.getName());
        DB.put(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deleteUser(@PathParam("id") int id) throws URISyntaxException {
        User user = DB.get(id);
        if(user != null) {
            DB.remove(user.getId());
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
}
