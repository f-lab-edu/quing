package flab.quing.waiting.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class WaitingResponse {

    private String storeName;

    private String userName;

    private String phoneNumber;

    private Integer countForward;

}
