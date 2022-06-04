package com.naveen.jersey_db.auth;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.util.DependenciesFactory;
import com.naveen.jersey_db.util.UserUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@Path("auth")
public class AuthResource {
    UserService userService;

    public AuthResource() {
        this.userService = DependenciesFactory.getUserService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response authenticate(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        try {
            System.out.println(authorization);
            int id = Integer.parseInt(UserUtils.getUserId(authorization));
            String password = UserUtils.getUserPassword(authorization);

            if (verifyIdPassword(id, password))
                return Response.ok(authorization).build();
        } catch (Exception e) {
            System.out.println("here..");
            System.out.println(e.getMessage());
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private boolean verifyIdPassword(int id, String password) {


        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            password = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();

            User user = userService.getUserById(id);
            System.out.println(
                    "username: " + user.getName()
                            + "\n dbPassword: " + user.getPassword()
                            + "\n given password: " + password
                            + "\n result:" + user.getPassword().equalsIgnoreCase(password));
            return user.getPassword().equalsIgnoreCase(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}


