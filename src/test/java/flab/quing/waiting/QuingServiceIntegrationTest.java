package flab.quing.waiting;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuingServiceIntegrationTest {

    @Autowired
    QuingService quingService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;


    @Test
    void append() {

        User user = userRepository.save(DummyDataMaker.user());
        Store store = storeRepository.save(DummyDataMaker.store());

        WaitingRequest waitingRequest = WaitingRequest.builder()
                .storeId(user.getId())
                .userId(store.getId())
                .build();

        WaitingResponse waitingResponse = quingService.append(waitingRequest);

        List<WaitingResponse> list = quingService.getList(store.getId());
//        System.out.println("list = " + list);
        list.forEach(System.out::println);

        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getUserName()).isEqualTo(user.getName());


    }

    @Test
    void getList() {
    }

    @Test
    void countForward() {
        Store store = DummyDataMaker.store();

        User user1 = DummyDataMaker.user();
        User user2 = DummyDataMaker.user();
        User user3 = DummyDataMaker.user();
        User user4 = DummyDataMaker.user();

        storeRepository.save(store);
        userRepository.saveAll(List.of(user1,user2,user3,user4));


        WaitingRequest waitingRequest1 = WaitingRequest.builder()
                .storeId(store.getId())
                .userId(user1.getId())
                .build();

        WaitingRequest waitingRequest2 = WaitingRequest.builder()
                .storeId(store.getId())
                .userId(user2.getId())
                .build();



        WaitingRequest waitingRequest3 = WaitingRequest.builder()
                .storeId(store.getId())
                .userId(user3.getId())
                .build();

        WaitingRequest waitingRequest4 = WaitingRequest.builder()
                .storeId(store.getId())
                .userId(user4.getId())
                .build();

        quingService.append(waitingRequest1);
        WaitingResponse waitingResponse2 = quingService.append(waitingRequest2);
        quingService.append(waitingRequest3);
        WaitingResponse waitingResponse4 = quingService.append(waitingRequest4);

        WaitingResponse doneWaiting = quingService.doneWaiting(waitingResponse2.getId());
        System.out.println("doneWaiting = " + doneWaiting);

        int forward = quingService.countForward(waitingResponse4.getId());

        List<WaitingResponse> list = quingService.getList(store.getId());
        list.forEach(System.out::println);

        assertThat(forward).isEqualTo(2);

    }

    @Test
    void sendMessage() {
    }

    @Test
    void sendEnterMessage() {
    }

    @Test
    void doneWaiting() {
    }

    @Test
    void cancelWaiting() {
    }
}