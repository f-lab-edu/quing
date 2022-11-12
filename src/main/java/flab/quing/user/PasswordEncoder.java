package flab.quing.user;

public interface PasswordEncoder {
    String hashPassword(String rawPassword);

    Boolean isMatched(String rawPassword, String hashedPassword);
}
