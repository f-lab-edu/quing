package flab.quing.review;

import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.waiting.NoSuchWaitingException;
import flab.quing.waiting.Waiting;
import flab.quing.waiting.WaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final WaitingRepository waitingRepository;


    @Transactional
    @Override
    public ReviewResponse create(ReviewRequest reviewRequest) {
        Waiting waiting = waitingRepository.findById(reviewRequest.getWaitingId())
                .orElseThrow(NoSuchWaitingException::new);
        Review review = Review.of(waiting.getUser(), waiting, reviewRequest);

        Review save = reviewRepository.save(review);
        return save.toResponse();
    }

    @Transactional
    @Override
    public ReviewResponse update(ReviewRequest reviewRequest) {
        Review review = reviewRepository.findTopByWaitingIdOrderByIdDesc(reviewRequest.getWaitingId())
                .orElseThrow(NoSuchReviewException::new);
        review.updateFrom(reviewRequest);
        return review.toResponse();
    }

    @Transactional
    @Override
    public ReviewResponse hide(long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(NoSuchReviewException::new);
        review.hide();
        return review.toResponse();
    }

    @Override
    public ReviewResponse getByReviewId(long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(NoSuchReviewException::new).toResponse();
    }

    @Override
    public ReviewResponse getByWaitingId(long waitingId) {
        return reviewRepository.findTopByWaitingIdOrderByIdDesc(waitingId)
                .orElseThrow(NoSuchReviewException::new).toResponse();
    }

    @Override
    public List<ReviewResponse> getList(long storeId) {
        return reviewRepository.findAllByWaitingStoreIdAndDeletedIsFalse(storeId)
                .stream()
                .map(Review::toResponse)
                .collect(Collectors.toList());
    }
}
