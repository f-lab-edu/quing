package flab.quing.waiting;

import flab.quing.store.exception.NoSuchStoreException;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.store.exception.NoSuchUserException;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import flab.quing.waiting.exception.DuplicateWaitingException;
import flab.quing.waiting.exception.NoSuchWaitingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuingServiceImpl implements QuingService {

    private final WaitingRepository waitingRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public WaitingResponse getByUserId(long userId) {
        return waitingRepository.findByUserIdAndWaitingQueueStatusIs(userId, WaitingQueueStatus.WAITING)
                .orElseThrow(NoSuchWaitingException::new)
                .toResponse();
    }

    @Override
    @Transactional
    public WaitingResponse append(WaitingRequest waitingRequest) {
        User user = userRepository.findById(waitingRequest.getUserId()).orElseThrow(NoSuchUserException::new);
        Store store = storeRepository.findById(waitingRequest.getStoreId()).orElseThrow(NoSuchStoreException::new);
        Waiting waiting = Waiting.of(user, store);

        Optional<Waiting> exist = waitingRepository.findByUserIdAndWaitingQueueStatusIs(user.getId(), WaitingQueueStatus.WAITING);
        if(exist.isPresent()) {
            throw new DuplicateWaitingException();
        }
        Waiting save = waitingRepository.save(waiting);
        return save.toResponse();
    }

    @Override
    public List<WaitingResponse> getList(long storeId) {
        return waitingRepository.findAllByStoreIdAndWaitingQueueStatusIs(storeId, WaitingQueueStatus.WAITING)
                .stream()
                .map(Waiting::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int countForward(long waitingId) {
        Waiting waiting = waitingRepository.findById(waitingId).orElseThrow(NoSuchWaitingException::new);
        List<WaitingResponse> list = getList(waiting.getStore().getId());
        return list.indexOf(waiting.toResponse());
    }

    @Override
    public void sendMessage(long waitingId, String message) {
        //ncloud sms
        Waiting waiting = waitingRepository.findById(waitingId).orElseThrow(NoSuchWaitingException::new);
        User user = waiting.getUser();
        System.out.println("phoneNumber = " + user.getPhoneNumber());
        System.out.println("message = " + user.getName()+"님 "+message);
    }

    @Override
    public void sendEnterMessage(long waitingId) {
        sendMessage(waitingId, "입장해주세요!");
    }

    @Override
    public void sendRegisterMessage(long waitingId) {
        sendMessage(waitingId, "등록되었습니다.");
    }

    @Override
    @Transactional
    public WaitingResponse doneWaiting(long waitingId) {
        Waiting waiting = waitingRepository.findById(waitingId).orElseThrow(NoSuchWaitingException::new);
        waiting.done();
        return waiting.toResponse();
    }

    @Override
    @Transactional
    public WaitingResponse cancelWaiting(long waitingId) {
        Waiting waiting = waitingRepository.findById(waitingId).orElseThrow(NoSuchWaitingException::new);
        waiting.cancel();
        return waiting.toResponse();
    }
}
