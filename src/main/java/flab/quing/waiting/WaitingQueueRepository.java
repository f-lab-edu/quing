package flab.quing.waiting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitingQueueRepository extends JpaRepository<Waiting, Long> {
    List<Waiting> findAllByStoreId(long storeId);
}
