package flab.quing.waiting;

import flab.quing.store.Store;
import flab.quing.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WaitingQueueService {

    private final WaitingQueueRepository waitingQueueRepository;

    public Waiting pushWaitingQueue(Store store, User member) {
        Waiting waitingQueue = Waiting.builder()
                .store(store)
                .member(member)
                .build();

        return waitingQueueRepository.save(waitingQueue);
    }

    public Waiting popWaitingQueue(Store store, User member) {

        throw new UnsupportedOperationException("Not Implemented, yet");
    }

    public List<Waiting> getWaitingQueue(Store store) {

        throw new UnsupportedOperationException("Not Implemented, yet");
    }

    public Waiting updateStatusWaitingQueue(Store store, User member, WaitingQueueStatus status) {

        throw new UnsupportedOperationException("Not Implemented, yet");
    }
}
