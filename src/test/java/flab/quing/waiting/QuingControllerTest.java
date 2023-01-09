package flab.quing.waiting;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.StoreManager;
import flab.quing.user.StoreManagerRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.waiting.dto.WaitingAppendRequest;
import flab.quing.waiting.dto.WaitingResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuingControllerTest {

    @Autowired
    QuingController quingController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    WaitingRepository waitingRepository;

    @Autowired
    StoreManagerRepository storeManagerRepository;

    DummyDataMaker dummyDataMaker = DummyDataMaker.builder().withId(false).build();

    @Test
    void 대기_등록(CapturedOutput output) {
        //given
        Store store = storeRepository.save(dummyDataMaker.store());

        User user = userRepository.save(dummyDataMaker.user());

        Waiting waiting = dummyDataMaker.waiting(user, store);

        WaitingAppendRequest waitingAppendRequest = WaitingAppendRequest.builder()
                .storeId(store.getId())
                .build();

        //when
        WaitingResponse append = quingController.append(waiting.getUser().toResponse(), waitingAppendRequest);

        //then
        assertThat(append.getUserName()).isEqualTo(user.getName());
        assertThat(output).contains("등록되었습니다.");
    }

    @Test
    void 내_앞에_대기중인_손님의_수() {
        //given
        final long userCount = 5;

        Store store = storeRepository.save(dummyDataMaker.store());

        List<User> users = new ArrayList<>();

        for (int i = 0; i < userCount; i++) {
            User user = userRepository.save(dummyDataMaker.user());
            users.add(user);

            WaitingAppendRequest waitingAppendRequest = WaitingAppendRequest.builder()
                    .storeId(store.getId())
                    .build();

            quingController.append(user.toResponse(), waitingAppendRequest);
        }

        //when
        long countForward = quingController.countForward(users.get((int) userCount - 1).toResponse());

        //then
        assertThat(countForward).isEqualTo(userCount - 1);
    }

    @Test
    void 가게에_대기중인_손님_리스트() {
        //given
        final long userCount = 5;

        Store store = storeRepository.save(dummyDataMaker.store());

        StoreManager storeManager = storeManagerRepository.save(StoreManager.builder()
                .name("test manager")
                .store(store)
                .build());

        for (int i = 0; i < userCount; i++) {
            User user = userRepository.save(dummyDataMaker.user());

            WaitingAppendRequest waitingAppendRequest = WaitingAppendRequest.builder()
                    .storeId(store.getId())
                    .build();

            quingController.append(user.toResponse(), waitingAppendRequest);
        }

        //when
        List<WaitingResponse> waitingResponseList = quingController.list(storeManager.toResponse());
        waitingResponseList.forEach(w-> log.info("waiting = " + w));
        //then
        assertThat(waitingResponseList.size()).isEqualTo(userCount);
    }

    @Test
    void 입장_완료() {
        //given
        StoreManagerResponse storeManagerResponse = StoreManagerResponse.builder().build();
        Store store = storeRepository.save(dummyDataMaker.store());
        User user = userRepository.save(dummyDataMaker.user());

        WaitingAppendRequest waitingAppendRequest = WaitingAppendRequest.builder()
                .storeId(store.getId())
                .build();

        WaitingResponse waitingResponse = quingController.append(user.toResponse(), waitingAppendRequest);

        //when
        WaitingResponse doneWaiting = quingController.doneWaiting(storeManagerResponse, user.getId());

        List<Waiting> all = waitingRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getWaitingQueueStatus()).isEqualTo(WaitingQueueStatus.DONE);
    }

    @Test
    void 대기_취소() {
        //given
        Store store = storeRepository.save(dummyDataMaker.store());
        User user = userRepository.save(dummyDataMaker.user());

        WaitingAppendRequest waitingAppendRequest = WaitingAppendRequest.builder()
                .storeId(store.getId())
                .build();

        WaitingResponse waitingResponse = quingController.append(user.toResponse(), waitingAppendRequest);

        //when
        quingController.cancelWaiting(user.toResponse());

        List<Waiting> all = waitingRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getWaitingQueueStatus()).isEqualTo(WaitingQueueStatus.CANCELED);
    }
}