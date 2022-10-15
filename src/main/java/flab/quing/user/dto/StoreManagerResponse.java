package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class StoreManagerResponse {

    private String loginId;
    private String password;
    private String phoneNumber;
    private String name;

}
