package flab.quing.waiting;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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
        list.forEach(o -> log.info("o = " + o));

        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getUserName()).isEqualTo(user.getName());
    }


    @Test
    void countForward() {
        Store store = storeRepository.save(DummyDataMaker.store());
        log.info("storeRepository = " + storeRepository.findAll());

        User user1 = userRepository.save(DummyDataMaker.user());
        User user2 = userRepository.save(DummyDataMaker.user());
        User user3 = userRepository.save(DummyDataMaker.user());
        User user4 = userRepository.save(DummyDataMaker.user());
        log.info("userRepository = " + userRepository.findAll());

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

        log.info("storeRepository.findAll() = " + storeRepository.findAll());
        log.info("waitingRequest1 = " + waitingRequest1);
        log.info("waitingRequest2 = " + waitingRequest2);
        log.info("waitingRequest3 = " + waitingRequest3);
        log.info("waitingRequest4 = " + waitingRequest4);

        WaitingResponse waitingResponse1 = quingService.append(waitingRequest1);
        WaitingResponse waitingResponse2 = quingService.append(waitingRequest2);
        WaitingResponse waitingResponse3 = quingService.append(waitingRequest3);
        WaitingResponse waitingResponse4 = quingService.append(waitingRequest4);

        WaitingResponse doneWaiting = quingService.doneWaiting(waitingResponse2.getId());
        log.info("doneWaiting = " + doneWaiting);

        int forward = quingService.countForward(waitingResponse4.getId());

        List<WaitingResponse> list = quingService.getList(store.getId());
        list.forEach(o -> log.info("o = " + o));

        assertThat(forward).isEqualTo(2);
    }
}