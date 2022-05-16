package com.naveen.HelloWorld;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(value = "hello")
public class Hello {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello World";
	}
}
