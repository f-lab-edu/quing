package flab.quing.review;

import flab.quing.review.dto.ReviewDeleteRequest;
import flab.quing.review.dto.ReviewPostRequest;
import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UserResponse;
import flab.quing.waiting.QuingService;
import flab.quing.waiting.dto.WaitingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final QuingService quingService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReviewResponse postReview(
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse,
            ReviewPostRequest reviewPostRequest
    ) {
        WaitingResponse userWaiting = quingService.getByUserId(userResponse.getUserId());
        ReviewRequest reviewRequest = ReviewRequest.builder()
                .userId(userResponse.getUserId())
                .waitingId(userWaiting.getId())
                .rating(reviewPostRequest.getRating())
                .message(reviewPostRequest.getMessage())
                .imageUrls(reviewPostRequest.getImageUrls())
                .build();

        return reviewService.create(reviewRequest);
    }

    @GetMapping("userReviewList")
    List<ReviewResponse> userReviewList(
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse
    ) {
        return reviewService.getListByUserId(userResponse.getUserId());
    }

    @GetMapping("storeReviewList")
    List<ReviewResponse> storeReviewList(
            @SessionAttribute(name = "AUTH_STORE")
            StoreManagerResponse storeManagerResponse
    ) {
        return reviewService.getListByStoreId(storeManagerResponse.getStoreId());
    }

    @GetMapping("{reviewId}")
    ReviewResponse getReview(@RequestParam Long reviewId) {
        return reviewService.getByReviewId(reviewId);
    }

    @DeleteMapping("{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReview(
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse,
            @RequestParam Long reviewId
    ) {
        ReviewDeleteRequest reviewDeleteRequest = ReviewDeleteRequest.builder()
                .userId(userResponse.getUserId())
                .reviewId(reviewId)
                .build();
        reviewService.hide(reviewDeleteRequest);
    }



}
