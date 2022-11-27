package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class UserResponse implements Serializable {

    private long userId;

    private String phoneNumber;

    private String name;

}
