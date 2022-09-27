package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class StoreMangerRequest {

    private String name;

    private String password;

    private String phoneNumber;

}
