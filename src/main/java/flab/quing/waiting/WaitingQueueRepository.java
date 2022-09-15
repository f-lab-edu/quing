package flab.quing.waiting;

import flab.quing.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitingQueueRepository extends JpaRepository<WaitingQueue, Long> {
    List<WaitingQueue> findAllByStore(Store store);
}
