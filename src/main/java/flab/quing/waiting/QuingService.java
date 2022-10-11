package flab.quing.waiting;

import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;

import java.util.List;

public interface QuingService {

    WaitingResponse append(WaitingRequest waitingRequest);

    List<WaitingResponse> getList(Long storeId);

    Integer countForward(Long waitingId);

    void sendMessage(Long waitingId, String message);

    void sendEnterMessage(Long waitingId);

    void doneWaiting(Long waitingId);

    void cancelWaiting(Long waitingId);

}
