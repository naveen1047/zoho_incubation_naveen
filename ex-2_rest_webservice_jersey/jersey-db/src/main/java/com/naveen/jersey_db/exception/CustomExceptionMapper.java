package com.naveen.jersey_db.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {
    @Override
    public Response toResponse(CustomException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDocumentation("http://my-website.com/doc");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setCode(500);

        return Response
                .status(500)
                .entity(errorMessage)
                .build();
    }
}
