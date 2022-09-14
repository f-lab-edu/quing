package flab.quing.review;

import flab.quing.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStore(Store store);
}
