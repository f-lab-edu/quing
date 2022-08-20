package flab.quing.service;

import flab.quing.domain.Member;
import flab.quing.domain.Store;
import flab.quing.domain.WaitingQueue;
import flab.quing.repository.WaitingQueueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WaitingQueueServiceTest {
// 등록 삭제(입장) 수정 리스트

    @InjectMocks
    WaitingQueueService waitingQueueService;

    @Mock
    WaitingQueueRepository waitingQueueRepository;

    Member dummyUser() {
        Member member = new Member();
        member.setName("dummy");
        member.setPassword("12341234");
        member.setPhone("010-1234-1234");
        return member;
    }

    Member dummyUser(String name) {
        Member member = dummyUser();
        member.setName(name);
        return member;
    }

    Store dummyStore() {
        Store store = new Store();
        store.setName("큐잉");
        return store;
    }

    @Test
    void 대기열등록_성공() {
        Store store = dummyStore();
        Member member = dummyUser();
        waitingQueueService = new WaitingQueueService(waitingQueueRepository);

        WaitingQueue waitingQueue = new WaitingQueue();
        waitingQueue.setMember(member);
        waitingQueue.setStore(store);

        Mockito.when(waitingQueueRepository.save(any()))
                .thenReturn(waitingQueue);

        WaitingQueue pushedItem = waitingQueueService.pushWaitingQueue(store, member);

        Assertions.assertThat(pushedItem.getMember().getName()).isEqualTo(member.getName());

    }

    //        Mockito.when(waitingQueueRepository.findAllByStore(any()))
//                .thenReturn(List.of(waitingQueue));

}