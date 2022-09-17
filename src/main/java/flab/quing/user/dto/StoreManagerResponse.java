package flab.quing.user.dto;

import lombok.Getter;

@Getter
public class StoreManagerResponse {

    private String loginId;
    private String password;
    private String phoneNumber;
    private String name;

    public StoreManagerResponse(String loginId, String password, String phoneNumber, String name) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
}
