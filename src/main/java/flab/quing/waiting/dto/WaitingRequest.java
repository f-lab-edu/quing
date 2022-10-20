package flab.quing.waiting.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class WaitingRequest {

    private long userId;

    private long storeId;
}
