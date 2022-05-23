//package com.naveen.jersey_db;
//
//import com.naveen.jersey_db.product.resource.ProductResource;
//import com.naveen.jersey_db.user.filter.SecurityFilter;
//import com.naveen.jersey_db.user.resource.UserResource;
//import jakarta.ws.rs.ApplicationPath;
//import org.glassfish.jersey.server.ResourceConfig;
//
//@ApplicationPath("webapi")
//public class Application extends ResourceConfig {
//    public Application() {
//        packages("com.naveen.jersey_db");
//        register(SecurityFilter.class);
//        register(UserResource.class);
//        register(ProductResource.class);
//    }
//}
