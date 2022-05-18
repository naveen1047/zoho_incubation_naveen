package com.naveen.jersey_db.product.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {
    @Override
    public Response toResponse(ProductNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(404);
        errorMessage.setMessage("Product not found");
        errorMessage.setDocumentation("http://my-website.com/doc");

        return Response
                .status(404)
                .entity(errorMessage)
                .build();
    }
}
