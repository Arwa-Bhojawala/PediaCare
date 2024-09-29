package com.cvac.springcvac.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthUtils {

    // This is a constant salt that will be used every time
    private static final String SALT = "Pediacare";
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((SALT + password).getBytes());
            byte[] hashedPassword = md.digest();

            // Convert the byte array to a Base64 encoded string
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            return null;
        }
    }
}
