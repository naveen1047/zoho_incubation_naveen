package com.naveen.jersey_db.user.filter;


import com.naveen.jersey_db.user.ErrorMessage;
import com.naveen.jersey_db.user.models.Role;
import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.util.DependenciesFactory;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.lang.reflect.Method;
import java.util.*;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final String docUrl = "http://my-website.com/doc";

    // This was throwing exception
    private static final ErrorMessage unauthorizedMessage =
            new ErrorMessage(docUrl, Response.Status.UNAUTHORIZED.getStatusCode(), "Access denied");
    private static final ErrorMessage forbiddenMessage =
            new ErrorMessage(docUrl, Response.Status.FORBIDDEN.getStatusCode(), "Access forbidden");
    private static final ErrorMessage internalServerErrorMessage =
            new ErrorMessage(docUrl, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "internal server error");

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
//            .entity(unauthorizedMessage)
            .build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
//            .entity(forbiddenMessage)
            .build();
    private static final Response SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//            .entity(internalServerErrorMessage)
            .build();
    private static User currentUser;

    UserService userService;

    public SecurityFilter() {
        this.userService = DependenciesFactory.getUserService();
    }

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = null;
            try {
                usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword));
            } catch (Exception e) {
                requestContext.abortWith(SERVER_ERROR);
                return;
            }

            //Split id and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String id = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            System.out.println(id);
            System.out.println(password);

            if (!(verifyIdPassword(Integer.parseInt(id), password))) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            if (currentUser == null) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            if (!isUserAllowed(id, password, currentUser.getRoles())) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

        }
    }


    private boolean verifyIdPassword(int id, String password) {
        try {
//            User user = userService.getUserByUsername(id);
            User user = userService.getUserById(id);
            currentUser = user;
            System.out.println(user.getName() + " pswd: "
                    + user.getPassword()
                    + "uPswd: " + password + " " + user.getPassword().equals(password));
            return user.getPassword().equals(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private boolean isUserAllowed(final String username, final String password, final Set<Role> rolesSet) {
        boolean isAllowed = false;

        if (rolesSet.contains(Role.ADMIN)) {
            isAllowed = true;
        }
        return isAllowed;
    }
}