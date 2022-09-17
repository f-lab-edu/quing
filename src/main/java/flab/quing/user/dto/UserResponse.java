package flab.quing.user.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private Long userId;
    private String phoneNumber;
    private String name;

    public UserResponse(Long userId, String phoneNumber, String name) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
}
