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



    @Test
    void 대기열등록_성공() {
        waitingQueueService = new WaitingQueueService(waitingQueueRepository);

        Store store = MakeDummy.store();
        Member member = MakeDummy.member();

        WaitingQueue waitingQueue = MakeDummy.waitingQueue(store, member);

        Mockito.when(waitingQueueRepository.save(any()))
                .thenReturn(waitingQueue);

        WaitingQueue pushedItem = waitingQueueService.pushWaitingQueue(store, member);

        Assertions.assertThat(pushedItem.getMember().getName()).isEqualTo(member.getName());

    }
    static class MakeDummy {

        static Member member() {
            Member member = new Member();
            member.setName("dummy");
            member.setPassword("12341234");
            member.setPhone("010-1234-1234");
            return member;
        }

        static Member member(String name) {
            Member member = MakeDummy.member();
            member.setName(name);
            return member;
        }

        static Store store() {
            Store store = new Store();
            store.setName("큐잉");
            return store;
        }

        static WaitingQueue waitingQueue(Store store, Member member) {
            WaitingQueue waitingQueue = new WaitingQueue();
            waitingQueue.setStore(store);
            waitingQueue.setMember(member);
            return waitingQueue;
        }
    }
    //        Mockito.when(waitingQueueRepository.findAllByStore(any()))
//                .thenReturn(List.of(waitingQueue));

}