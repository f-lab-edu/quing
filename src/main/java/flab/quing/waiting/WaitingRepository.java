package flab.quing.waiting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {

    Optional<Waiting> findByUserIdAndWaitingQueueStatusIs(long userId, WaitingQueueStatus waitingQueueStatus);

    List<Waiting> findAllByStoreIdAndWaitingQueueStatusIs(long storeId, WaitingQueueStatus waitingQueueStatus);
}
