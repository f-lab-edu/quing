package flab.quing.review;

import flab.quing.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByStore(Store store);
}
