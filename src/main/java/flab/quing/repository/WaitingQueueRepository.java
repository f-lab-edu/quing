package flab.quing.repository;

import flab.quing.domain.WaitingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingQueueRepository extends JpaRepository<WaitingQueue, Long> {
}
