package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.waiting.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByWaitingStoreAndDeletedIsFalse(Store store);

    Optional<Review> findByWaitingOrderByIdDesc(Waiting waiting);

}
