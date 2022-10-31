package flab.quing.waiting;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import flab.quing.waiting.exception.DuplicateWaitingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuingServiceImplTest {

    @InjectMocks
    QuingServiceImpl quingService;

    @Mock
    UserRepository userRepository;

    @Mock
    StoreRepository storeRepository;

    @Mock
    WaitingRepository waitingRepository;

    @Test
    void append() {

        User user = DummyDataMaker.user();
        Store store = DummyDataMaker.store();
        WaitingRequest waitingRequest = WaitingRequest.builder()
                .userId(user.getId())
                .storeId(store.getId())
                .build();
        Waiting waiting = DummyDataMaker.waiting(user, store);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(waitingRepository.findByUserIdAndWaitingQueueStatusIs(anyLong(), any())).thenReturn(Optional.empty());
        when(waitingRepository.save(any())).thenReturn(waiting);

        WaitingResponse waitingResponse = quingService.append(waitingRequest);
        System.out.println("waitingResponse = " + waitingResponse);

        assertThat(waitingResponse.getId()).isEqualTo(1);
    }

    @Test
    void append_exist_throw_exception() {
        User user = DummyDataMaker.user();
        Store store = DummyDataMaker.store();
        WaitingRequest waitingRequest = WaitingRequest.builder()
                .userId(user.getId())
                .storeId(store.getId())
                .build();
        Waiting waiting = DummyDataMaker.waiting(user, store);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(waitingRepository.findByUserIdAndWaitingQueueStatusIs(anyLong(), any())).thenReturn(Optional.of(waiting));

        assertThatThrownBy(() -> {
            quingService.append(waitingRequest);
        }).isInstanceOf(DuplicateWaitingException.class);

    }

    @Test
    void getList() {
        User user1 = DummyDataMaker.user();
        User user2 = DummyDataMaker.user();
        User user3 = DummyDataMaker.user();
        Store store = DummyDataMaker.store();
        Waiting waiting1 = DummyDataMaker.waiting(user1, store);
        Waiting waiting2 = DummyDataMaker.waiting(user2, store);
        Waiting waiting3 = DummyDataMaker.waiting(user3, store);

        when(waitingRepository.findAllByStoreIdAndWaitingQueueStatusIs(anyLong(),any(WaitingQueueStatus.class)))
                .thenReturn(List.of(waiting1,waiting2,waiting3));

        List<WaitingResponse> result = quingService.getList(1L);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(1)).isEqualTo(waiting2.toResponse());

    }

    @Test
    void countForward() {
        User user1 = DummyDataMaker.user();
        User user2 = DummyDataMaker.user();
        User user3 = DummyDataMaker.user();
        Store store = DummyDataMaker.store();
        Waiting waiting1 = DummyDataMaker.waiting(user1, store);
        Waiting waiting2 = DummyDataMaker.waiting(user2, store);
        Waiting waiting3 = DummyDataMaker.waiting(user3, store);

        when(waitingRepository.findById(3L)).thenReturn(Optional.of(waiting3));
        when(waitingRepository.findAllByStoreIdAndWaitingQueueStatusIs(anyLong(),any(WaitingQueueStatus.class)))
                .thenReturn(List.of(waiting1,waiting2,waiting3));

        int index = quingService.countForward(3L);

        assertThat(index).isEqualTo(2);
    }

    @Test
    void sendMessage() {
        quingService.sendMessage(1L, "test");
    }

    @Test
    void sendEnterMessage() {
        quingService.sendEnterMessage(1L);
    }

    @Test
    void doneWaiting() {
        quingService.doneWaiting(1L);
    }

    @Test
    void cancelWaiting() {
        quingService.cancelWaiting(1L);
    }
}