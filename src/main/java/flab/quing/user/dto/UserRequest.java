package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class UserRequest {

    private String name;

    private String phoneNumber;

}
