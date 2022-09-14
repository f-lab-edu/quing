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

    public WaitingQueue pushWaitingQueue(Store store, User member) {
        WaitingQueue waitingQueue = new WaitingQueue();
        waitingQueue.setStore(store);
        waitingQueue.setMember(member);
        WaitingQueue save = waitingQueueRepository.save(waitingQueue);
//        List<WaitingQueue> allByStore = waitingQueueRepository.findAllByStore(store);

        return save;
    }

    public WaitingQueue popWaitingQueue(Store store, User member) {

        return null;
    }

    public List<WaitingQueue> getWaitingQueue(Store store) {

        return null;
    }

    public WaitingQueue updateStatusWaitingQueue(Store store, User member, WaitingQueueStatus status) {

        return null;
    }
}
