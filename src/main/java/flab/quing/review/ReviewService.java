package flab.quing.review;


import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;

import java.util.List;

//Chanwoo
public interface ReviewService {

    ReviewResponse create(ReviewRequest reviewRequest);

    ReviewResponse update(ReviewRequest reviewRequest);

    ReviewResponse hide(long reviewId);

    ReviewResponse get(long reviewId);

    ReviewResponse find(long waitingId);

    List<ReviewResponse> getList(long storeId);

}
