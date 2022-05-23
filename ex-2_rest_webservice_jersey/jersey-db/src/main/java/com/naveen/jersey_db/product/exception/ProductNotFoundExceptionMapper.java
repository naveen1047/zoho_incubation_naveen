package com.naveen.jersey_db.product.exception;

import com.naveen.jersey_db.exception.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {
    @Override
    public Response toResponse(ProductNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(NOT_FOUND.getStatusCode());
        errorMessage.setMessage("Product not found");
        errorMessage.setDocumentation("http://my-website.com/doc");

        return Response
                .status(NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
