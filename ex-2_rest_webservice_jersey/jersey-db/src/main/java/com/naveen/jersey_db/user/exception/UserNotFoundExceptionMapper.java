package com.naveen.jersey_db.user.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(404);
        errorMessage.setMessage("user not found");
        errorMessage.setDocumentation("http://my-website.com/doc");

        return Response
                .status(404)
                .entity(errorMessage)
                .build();
    }
}
