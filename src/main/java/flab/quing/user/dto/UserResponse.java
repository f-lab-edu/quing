package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class UserResponse {

    private long userId;

    private String phoneNumber;

    private String name;

}
