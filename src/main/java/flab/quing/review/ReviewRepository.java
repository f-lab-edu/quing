package flab.quing.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndDeletedIsFalse(long reviewId);

    List<Review> findAllByWaitingStoreIdAndDeletedIsFalse(long storeId);

    List<Review> findAllByWaitingUserIdAndDeletedIsFalse(long userId);

    Optional<Review> findTopByWaitingIdOrderByIdDesc(long waitingId);
}
