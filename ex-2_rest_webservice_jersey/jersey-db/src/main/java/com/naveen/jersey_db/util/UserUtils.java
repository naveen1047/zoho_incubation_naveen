package com.naveen.jersey_db.util;

import java.util.Base64;
import java.util.StringTokenizer;

public class UserUtils {
    public static boolean compareUserIdAuthId(String encryptedText, String id) {
        return getUserId(encryptedText).equals(id);
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
