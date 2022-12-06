package flab.quing.review;


import flab.quing.review.dto.ReviewDeleteRequest;
import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse create(ReviewRequest reviewRequest);

    ReviewResponse update(ReviewRequest reviewRequest);

    ReviewResponse hide(ReviewDeleteRequest reviewDeleteRequest);

    ReviewResponse getByReviewId(long reviewId);

    ReviewResponse getByWaitingId(long waitingId);

    List<ReviewResponse> getListByStoreId(long storeId);

    List<ReviewResponse> getListByUserId(long userId);

}
