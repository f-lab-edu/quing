package flab.quing.service;

import flab.quing.domain.Member;
import flab.quing.domain.Store;
import flab.quing.domain.WaitingQueue;
import flab.quing.domain.WaitingQueueStatus;
import flab.quing.repository.WaitingQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WaitingQueueService {

    private final WaitingQueueRepository waitingQueueRepository;

    public WaitingQueue pushWaitingQueue(Store store, Member member) {
        WaitingQueue waitingQueue = new WaitingQueue();
        waitingQueue.setStore(store);
        waitingQueue.setMember(member);
        WaitingQueue save = waitingQueueRepository.save(waitingQueue);
//        List<WaitingQueue> allByStore = waitingQueueRepository.findAllByStore(store);

        return save;
    }

    public WaitingQueue popWaitingQueue(Store store, Member member) {

        return null;
    }

    public List<WaitingQueue> getWaitingQueue(Store store) {

        return null;
    }

    public WaitingQueue updateStatusWaitingQueue(Store store, Member member, WaitingQueueStatus status) {

        return null;
    }
}
