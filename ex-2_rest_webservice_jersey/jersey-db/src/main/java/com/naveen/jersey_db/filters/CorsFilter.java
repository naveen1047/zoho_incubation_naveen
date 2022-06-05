package com.naveen.jersey_db.filters;

import com.naveen.jersey_db.util.UserUtils;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import javax.ws.rs.container.PreMatching;
import java.io.IOException;

@Provider
//@PreMatching
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        System.out.println("......filters working........");
        System.out.println(responseContext.getStatus());

        System.out.println(requestContext.getHeaders());

        if (responseContext.getHeaders().get("Access-Control-Allow-Origin") == null) {
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Headers",
                    "Origin, Accept, X-Requested-With, Content-Type," +
                            " Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        }
    }

}