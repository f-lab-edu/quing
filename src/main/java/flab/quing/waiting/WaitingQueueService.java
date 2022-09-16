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
        Waiting waitingQueue = new Waiting();
        waitingQueue.setStore(store);
        waitingQueue.setMember(member);
        Waiting saveResult = waitingQueueRepository.save(waitingQueue);
//        List<WaitingQueue> allByStore = waitingQueueRepository.findAllByStore(store);

        return saveResult;
    }

    public Waiting popWaitingQueue(Store store, User member) {

        return null;
    }

    public List<Waiting> getWaitingQueue(Store store) {

        return null;
    }

    public Waiting updateStatusWaitingQueue(Store store, User member, WaitingQueueStatus status) {

        return null;
    }
}
