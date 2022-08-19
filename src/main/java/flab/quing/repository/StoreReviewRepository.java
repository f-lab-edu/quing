package flab.quing.repository;

import flab.quing.domain.Store;
import flab.quing.domain.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
    List<StoreReview> findAllByStore(Store store);
}
