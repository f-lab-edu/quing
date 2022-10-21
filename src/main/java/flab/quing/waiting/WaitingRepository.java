package flab.quing.waiting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {

    List<Waiting> findAllByStoreIdAndWaitingQueueStatusIs(long storeId, WaitingQueueStatus waitingQueueStatus);
}
