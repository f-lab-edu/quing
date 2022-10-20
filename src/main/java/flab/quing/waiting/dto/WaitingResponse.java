package flab.quing.waiting.dto;

import flab.quing.waiting.WaitingQueueStatus;
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

    private int countForward;

    private int callCount;

    private WaitingQueueStatus waitingQueueStatus;

}
