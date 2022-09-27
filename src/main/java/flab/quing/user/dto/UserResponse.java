package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class UserResponse {

    private long userId;

    private String phoneNumber;

    private String name;

}
