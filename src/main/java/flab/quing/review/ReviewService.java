package flab.quing.review;


import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;

import java.util.List;

//Chanwoo
public interface ReviewService {

    ReviewResponse create(ReviewRequest reviewRequest);

    ReviewResponse update(ReviewRequest reviewRequest);

    ReviewResponse hide(Long reviewId);

    ReviewResponse get(Long reviewId);

    List<ReviewResponse> getList(Long storeId);

}
