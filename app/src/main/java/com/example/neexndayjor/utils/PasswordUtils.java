package com.example.neexndayjor.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Hash the password using SHA-256 (for demonstration purposes, but bcrypt is more secure)
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Compare the password with the stored hashed password
    public static boolean checkPassword(String inputPassword, String storedHashedPassword) {
        return hashPassword(inputPassword).equals(storedHashedPassword);
    }
}
