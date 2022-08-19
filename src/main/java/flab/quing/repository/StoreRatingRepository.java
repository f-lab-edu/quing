package flab.quing.repository;

import flab.quing.domain.Store;
import flab.quing.domain.StoreRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRatingRepository extends JpaRepository<StoreRating, Long> {
    Optional<StoreRating> findByStore(Store store);
}
