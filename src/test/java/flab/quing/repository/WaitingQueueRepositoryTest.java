package flab.quing.repository;

import flab.quing.domain.Member;
import flab.quing.domain.Store;
import flab.quing.domain.WaitingQueue;
import flab.quing.domain.WaitingQueueStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WaitingQueueRepositoryTest {

    @Autowired
    WaitingQueueRepository waitingQueueRepository;

    @Test
    void 대기열에_손님을_등록한다() {
        Store store = new Store();
        store.setName("큐잉상점");

        Member member1 = new Member();
        member1.setName("누누");

        WaitingQueue waitingQueue = new WaitingQueue();
        waitingQueue.setStore(store);
        waitingQueue.setMember(member1);

        waitingQueueRepository.save(waitingQueue);
        List<WaitingQueue> waitingQueues = waitingQueueRepository.findAllByStore(store);

        assertThat(waitingQueues.size()).isEqualTo(1);
        assertThat(waitingQueues.get(0).getMember().getName()).isEqualTo(member1.getName());
    }

    @Test
    void 대기열에_등록된_손님이_입장한다() {
        Store store = new Store();
        store.setName("큐잉상점");

        Member member1 = new Member();
        member1.setName("누누");

        WaitingQueue waitingQueue = new WaitingQueue();
        waitingQueue.setStore(store);
        waitingQueue.setMember(member1);
        waitingQueue.setWaitingQueueStatus(WaitingQueueStatus.WAITING);

        waitingQueueRepository.save(waitingQueue);
        List<WaitingQueue> waitingQueues = waitingQueueRepository.findAllByStore(store);

        waitingQueues.get(0).setWaitingQueueStatus(WaitingQueueStatus.DONE);

        assertThat(waitingQueues.size()).isEqualTo(1);
        assertThat(waitingQueues.get(0).getMember().getName()).isEqualTo(member1.getName());
        assertThat(waitingQueues.get(0).getWaitingQueueStatus()).isEqualTo(WaitingQueueStatus.DONE);
    }

}