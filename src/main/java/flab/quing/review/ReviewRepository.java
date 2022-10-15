package flab.quing.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByWaitingStoreIdAndDeletedIsFalse(long storeId);

    Optional<Review> findTopByWaitingIdOrderByIdDesc(long waitingId);
}
