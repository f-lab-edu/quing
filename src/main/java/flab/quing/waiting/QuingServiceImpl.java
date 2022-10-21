package flab.quing.waiting;

import flab.quing.store.NoSuchStoreException;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.store.exception.NoSuchUserException;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuingServiceImpl implements QuingService {

    private final WaitingRepository waitingRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public WaitingResponse append(WaitingRequest waitingRequest) {
        User user = userRepository.findById(waitingRequest.getUserId()).orElseThrow(NoSuchUserException::new);
        Store store = storeRepository.findById(waitingRequest.getStoreId()).orElseThrow(NoSuchStoreException::new);
        Waiting waiting = Waiting.of(user, store);
        Waiting save = waitingRepository.save(waiting);

        return save.toResponse();
    }

    @Override
    public List<WaitingResponse> getList(long storeId) {
//        return waitingRepository.findAllByStoreId(storeId)
        return waitingRepository.findAllByStoreIdAndWaitingQueueStatusIs(storeId, WaitingQueueStatus.WAITING)
                .stream()
                .map(Waiting::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public int countForward(long waitingId) {
        Waiting waiting = waitingRepository.findById(waitingId).orElseThrow(NoSuchWaitingException::new);
        List<WaitingResponse> list = getList(waiting.getStore().getId());
        return list.indexOf(waiting.toResponse());
    }

    @Override
    public void sendMessage(long waitingId, String message) {

    }

    @Override
    public void sendEnterMessage(long waitingId) {

    }

    @Override
    @Transactional
    public WaitingResponse doneWaiting(long waitingId) {
        Waiting waiting = waitingRepository.findById(waitingId).orElseThrow(NoSuchWaitingException::new);
        waiting.done();
        return waiting.toResponse();
    }

    @Override
    public void cancelWaiting(long waitingId) {

    }
}
