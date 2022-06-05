package com.naveen.jersey_db.util;

import com.naveen.jersey_db.user.models.User;
import com.naveen.jersey_db.user.service.UserService;
import com.naveen.jersey_db.user.util.DependenciesFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.StringTokenizer;

public class UserUtils {
    static UserService userService;

    public UserUtils() {
        this.userService = DependenciesFactory.getUserService();
    }
    public static boolean compareUserIdAuthId(String encryptedText, String id) {
        return getUserId(encryptedText).equals(id);
    }

    public static boolean verifyIdPassword(int id, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            password = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();

            User user = userService.getUserById(id);
            System.out.println(
                    "username: " + user.getName()
                            + "\n dbPassword: " + user.getPassword()
                            + "\n given password: " + password
                            + "\n result:" + user.getPassword().equalsIgnoreCase(password));
            return user.getPassword().equalsIgnoreCase(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static String getUserId(String usernamePassword) {
        String AUTHENTICATION_SCHEME = "Basic";
        String encodeUsernamePassword = usernamePassword.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        String userpass = new String(Base64.getDecoder().decode(encodeUsernamePassword));
        StringTokenizer tokenizer = new StringTokenizer(userpass, ":");
        return tokenizer.nextToken();
    }

    public static String getUserPassword(String usernamePassword) {
        String AUTHENTICATION_SCHEME = "Basic";
        String encodeUsernamePassword = usernamePassword.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        String userpass = new String(Base64.getDecoder().decode(encodeUsernamePassword));
        StringTokenizer tokenizer = new StringTokenizer(userpass, ":");
        tokenizer.nextToken();
        return tokenizer.nextToken();
    }
}
