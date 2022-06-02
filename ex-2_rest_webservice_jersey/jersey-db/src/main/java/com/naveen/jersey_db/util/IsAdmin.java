package com.naveen.jersey_db.util;

import com.naveen.jersey_db.exception.CustomException;
import com.naveen.jersey_db.wallet.Wallet;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Path("isAdmin")
public class IsAdmin {
    @GET
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response isAdmin(@PathParam("id") int id,
                              @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id)))
            throw new CustomException("Id didn't match");
        return Response.ok().build();
    }
}
