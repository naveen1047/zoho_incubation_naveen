package com.naveen.jersey_db.filters;

import com.opencsv.CSVWriter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Request filter:");
        System.out.println("headers: " + requestContext.getHeaders());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("Response filter:");

        final String path = "C:\\Users\\LENOVO\\OneDrive\\Desktop\\zoho incu\\" +
                "zoho exercise\\" +
                "ex-2_rest_webservice_jersey\\jersey-db\\downloads\\report.csv";

        String uri = String.valueOf(requestContext.getUriInfo().getPath());

        System.out.println(Arrays.stream(uri.split("/")).findFirst().get().equals("users"));
        System.out.println(requestContext.getMethod().equalsIgnoreCase("POST"));

        boolean compare = Arrays.stream(uri.split("/")).findFirst().get().equals("users")
                && requestContext.getMethod().equalsIgnoreCase("POST");

        if (!requestContext.getMethod().equalsIgnoreCase("GET")
                && !compare
        ) {
            String method = requestContext.getMethod();
            String status = String.valueOf(responseContext.getStatus());
            String date = LocalDateTime.now().toString();
            String id = getId(requestContext);


            try (CSVWriter writer = new CSVWriter(new FileWriter(path, true))) {
                writer.writeNext(new String[]
                        {id, date, uri, method, status, message(id, date, uri, method, status)});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("headers: " + requestContext.getHeaders());
    }

    private String message(String id, String date, String uri, String method, String status) {
        String action = Arrays.stream(uri.split("/")).findFirst().get();
        String methodMessage = methodMessage(Integer.parseInt(status));
        return action + " " + methodMessage + " for " + id;
    }

    private String methodMessage(int method) {
//        HashMap<Integer, String> hm = new HashMap<>();
//
//        hm.put(200, "successful");
//        hm.put(500, "not successful");
//
//        if (hm.containsKey(method)) {
//            return hm.get(method);
//        }

        if (method == 201) {
            return "created successful";
        }
        if (method >= 200 && method <= 300) {
            return "successful";
        }
        if (method >= 400) {
            return "not successful";
        }
        return "";
    }

    private String getId(ContainerRequestContext requestContext) {
        String AUTHORIZATION_PROPERTY = "Authorization";
        String AUTHENTICATION_SCHEME = "Basic";

        String usernamePassword = requestContext
                .getHeaders()
                .get(AUTHORIZATION_PROPERTY)
                .stream()
                .findFirst()
                .get();
        String encodeUsernamePassword = usernamePassword.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        String userpass = new String(Base64.getDecoder().decode(encodeUsernamePassword));
        StringTokenizer tokenizer = new StringTokenizer(userpass, ":");
        return tokenizer.nextToken();
    }
}
