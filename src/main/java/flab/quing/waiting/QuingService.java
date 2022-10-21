package flab.quing.waiting;

import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;

import java.util.List;

public interface QuingService {

    WaitingResponse append(WaitingRequest waitingRequest);

    List<WaitingResponse> getList(long storeId);

    int countForward(long waitingId);

    void sendMessage(long waitingId, String message);

    void sendEnterMessage(long waitingId);

    WaitingResponse doneWaiting(long waitingId);

    void cancelWaiting(long waitingId);

}
