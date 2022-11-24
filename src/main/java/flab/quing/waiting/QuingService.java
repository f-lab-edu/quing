package flab.quing.waiting;

import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;

import java.util.List;

public interface QuingService {

    WaitingResponse getByUserId(long userId);

    WaitingResponse append(WaitingRequest waitingRequest);

    List<WaitingResponse> getList(long storeId);

    int countForward(long waitingId);

    void sendMessage(long waitingId, String message);

    void sendEnterMessage(long waitingId);

    void sendRegisterMessage(long waitingId);

    WaitingResponse doneWaiting(long waitingId);

    WaitingResponse cancelWaiting(long waitingId);

}
