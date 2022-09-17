package flab.quing.review;

import flab.quing.review.dto.RatingResponse;

public interface RatingService {

    RatingResponse getRating(Long storeId);

    /**
     * 모든 상점의 리뷰와 별점을 계산해서 각 상점별 별점을 갱신합니다.
     */
    void updateRating();

}
