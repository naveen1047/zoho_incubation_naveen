package com.naveen.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("sam")
public class sampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "smaple ist!asf";
    }

}
