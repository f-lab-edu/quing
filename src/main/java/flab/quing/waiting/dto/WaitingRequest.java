package flab.quing.waiting.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class WaitingRequest {

    private Long userId;

    private String phoneNumber;

    @NonNull
    private Long storeId;
}
