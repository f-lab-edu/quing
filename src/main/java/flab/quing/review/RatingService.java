package flab.quing.review;

import flab.quing.review.dto.RatingResponse;

public interface RatingService {

    RatingResponse getRating(Long storeId);

    void updateAll();

}
