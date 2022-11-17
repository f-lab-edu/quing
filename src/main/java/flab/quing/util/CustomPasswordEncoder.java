package flab.quing.util;

import flab.quing.util.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public final class CustomPasswordEncoder {

    public static String hashPassword(String rawPassword) {
        return sha512(rawPassword);
    }

    public static Boolean isMatched(String rawPassword, String hashedPassword) {
        String encryptedPassword = String.valueOf(sha512(rawPassword));
        return encryptedPassword.equals(hashedPassword);
    }

    private static String sha512(String rawPassword) {
        String encryptedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(rawPassword.getBytes(StandardCharsets.UTF_8));
            encryptedPassword = String.format("%0128x", new BigInteger(1, md.digest()));
            return encryptedPassword;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
