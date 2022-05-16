package com.naveen.CRUDjersey;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("newresource")
public class NewResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response newResourse( ) {
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
