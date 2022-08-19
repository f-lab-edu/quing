package flab.quing.repository;

import flab.quing.domain.Store;
import flab.quing.domain.WaitingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitingQueueRepository extends JpaRepository<WaitingQueue, Long> {
    List<WaitingQueue> findAllByStore(Store store);
}
