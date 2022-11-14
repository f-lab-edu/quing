package flab.quing.user;

import flab.quing.util.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Slf4j
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String hashPassword(String rawPassword) {
        return sha512(rawPassword);
    }

    @Override
    public Boolean isMatched(String rawPassword, String hashedPassword) {
        String encryptedPassword = String.valueOf(sha512(rawPassword));
        return encryptedPassword.equals(hashedPassword);
    }

    private String sha512(String rawPassword) {
        String encryptedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(rawPassword.getBytes(StandardCharsets.UTF_8));
            encryptedPassword = String.format("%0128x", new BigInteger(1, md.digest()));
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException("rawPassword cannot be null");
        }
    }
}
