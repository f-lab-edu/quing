package flab.quing.service;

import flab.quing.user.User;
import flab.quing.store.Store;
import flab.quing.waiting.WaitingQueue;
import flab.quing.waiting.WaitingQueueRepository;
import flab.quing.waiting.WaitingQueueService;
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
        User member = MakeDummy.member();

        WaitingQueue waitingQueue = MakeDummy.waitingQueue(store, member);

        Mockito.when(waitingQueueRepository.save(any()))
                .thenReturn(waitingQueue);

        WaitingQueue pushedItem = waitingQueueService.pushWaitingQueue(store, member);

        Assertions.assertThat(pushedItem.getMember().getName()).isEqualTo(member.getName());

    }
    static class MakeDummy {

        static User member() {
            User member = new User();
            member.setName("dummy");
            member.setPassword("12341234");
            member.setPhone("010-1234-1234");
            return member;
        }

        static User member(String name) {
            User member = MakeDummy.member();
            member.setName(name);
            return member;
        }

        static Store store() {
            Store store = new Store();
            store.setName("큐잉");
            return store;
        }

        static WaitingQueue waitingQueue(Store store, User member) {
            WaitingQueue waitingQueue = new WaitingQueue();
            waitingQueue.setStore(store);
            waitingQueue.setMember(member);
            return waitingQueue;
        }
    }
    //        Mockito.when(waitingQueueRepository.findAllByStore(any()))
//                .thenReturn(List.of(waitingQueue));

}