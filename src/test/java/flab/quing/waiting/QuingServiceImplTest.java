package flab.quing.waiting;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import flab.quing.waiting.exception.DuplicateWaitingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class QuingServiceImplTest {

    @InjectMocks
    QuingServiceImpl quingService;

    @Mock
    UserRepository userRepository;

    @Mock
    StoreRepository storeRepository;

    @Mock
    WaitingRepository waitingRepository;

    @Mock
    ConsoleLogMessageSender messageSender;

    DummyDataMaker dummyDataMaker = DummyDataMaker.builder().withId(true).build();

    @Test
    void append() {
        //given
        User user = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        WaitingRequest waitingRequest = WaitingRequest.builder()
                .userId(user.getId())
                .storeId(store.getId())
                .build();
        Waiting waiting = dummyDataMaker.waiting(user, store);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(waitingRepository.findByUserIdAndWaitingQueueStatusIs(anyLong(), any())).thenReturn(Optional.empty());
        when(waitingRepository.save(any())).thenReturn(waiting);

        //when
        WaitingResponse waitingResponse = quingService.append(waitingRequest);
        log.debug("waitingResponse = " + waitingResponse);

        //then
        assertThat(waitingResponse.getId()).isEqualTo(1);
    }

    @Test
    void append_exist_throw_exception() {
        //given
        User user = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        WaitingRequest waitingRequest = WaitingRequest.builder()
                .userId(user.getId())
                .storeId(store.getId())
                .build();
        Waiting waiting = dummyDataMaker.waiting(user, store);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(waitingRepository.findByUserIdAndWaitingQueueStatusIs(anyLong(), any())).thenReturn(Optional.of(waiting));

        //when
        //then
        assertThatThrownBy(() ->
                quingService.append(waitingRequest))
                .isInstanceOf(DuplicateWaitingException.class);

    }

    @Test
    void getList() {
        //given
        User user1 = dummyDataMaker.user();
        User user2 = dummyDataMaker.user();
        User user3 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);
        Waiting waiting2 = dummyDataMaker.waiting(user2, store);
        Waiting waiting3 = dummyDataMaker.waiting(user3, store);

        when(waitingRepository.findAllByStoreIdAndWaitingQueueStatusIs(anyLong(), any(WaitingQueueStatus.class)))
                .thenReturn(List.of(waiting1, waiting2, waiting3));

        //when
        List<WaitingResponse> result = quingService.getList(1L);

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(1)).isEqualTo(waiting2.toResponse());
    }

    @Test
    void countForward() {
        //given
        User user1 = dummyDataMaker.user();
        User user2 = dummyDataMaker.user();
        User user3 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);
        Waiting waiting2 = dummyDataMaker.waiting(user2, store);
        Waiting waiting3 = dummyDataMaker.waiting(user3, store);

        when(waitingRepository.findById(3L)).thenReturn(Optional.of(waiting3));
        when(waitingRepository.findAllByStoreIdAndWaitingQueueStatusIs(anyLong(), any(WaitingQueueStatus.class)))
                .thenReturn(List.of(waiting1, waiting2, waiting3));

        //when
        int index = quingService.countForward(3L);

        //then
        assertThat(index).isEqualTo(2);
    }

    @Test
    void sendMessage(CapturedOutput output) {
        //given
        User user1 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);

        when(waitingRepository.findById(1L)).thenReturn(Optional.of(waiting1));
        when(messageSender.send(any(), any())).thenCallRealMethod();

        //when
        quingService.sendMessage(1L, "test");

        //then
        assertThat(output).contains("test");
    }

    @Test
    void sendEnterMessage(CapturedOutput output) {
        //given
        User user1 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);

        when(waitingRepository.findById(1L)).thenReturn(Optional.of(waiting1));
        when(messageSender.send(any(), any())).thenCallRealMethod();

        //when
        quingService.sendEnterMessage(1L);

        //then
        assertThat(output).contains("입장");

    }

    @Test
    void sendRegisterMessage(CapturedOutput output) {
        //given
        User user1 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);

        when(waitingRepository.findById(1L)).thenReturn(Optional.of(waiting1));
        when(messageSender.send(any(), any())).thenCallRealMethod();

        //when
        quingService.sendRegisterMessage(1L);

        //then
        assertThat(output).contains("등록");
    }

    @Test
    void doneWaiting() {
        //given
        User user1 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);

        when(waitingRepository.findById(anyLong())).thenReturn(Optional.ofNullable(waiting1));

        //when
        WaitingResponse waitingResponse = quingService.doneWaiting(1L);
        log.debug("waitingResponse = " + waitingResponse);

        //then
        assertThat(waitingResponse.getWaitingQueueStatus()).isEqualTo(WaitingQueueStatus.DONE);
    }

    @Test
    void cancelWaiting() {
        //given
        User user1 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);

        when(waitingRepository.findById(anyLong())).thenReturn(Optional.ofNullable(waiting1));

        //when
        WaitingResponse waitingResponse = quingService.cancelWaiting(1L);
        log.debug("waitingResponse = " + waitingResponse);

        //then
        assertThat(waitingResponse.getWaitingQueueStatus()).isEqualTo(WaitingQueueStatus.CANCELED);
    }
}