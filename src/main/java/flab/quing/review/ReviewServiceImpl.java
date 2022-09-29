package flab.quing.review;

import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
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
    private final StoreRepository storeRepository;
    private final WaitingRepository waitingRepository;


    @Transactional
    @Override
    public ReviewResponse create(ReviewRequest reviewRequest) {
        Waiting waiting = waitingRepository.findById(reviewRequest.getWaitingId()).get();

        User user = waiting.getUser();

        Review review = Review.builder()
                .user(user)
                .waiting(waiting)
                .build();
        review.of(reviewRequest);


        Review save = reviewRepository.save(review);
        return save.toResponse();
    }

    @Transactional
    @Override
    public ReviewResponse update(ReviewRequest reviewRequest) {
        Waiting waiting = waitingRepository.findById(reviewRequest.getWaitingId()).get();
        Review review = reviewRepository.findByWaitingOrderByIdDesc(waiting).get();
        hide(review.getId());
        return create(reviewRequest);
    }

    @Transactional
    @Override
    public ReviewResponse hide(long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        review.hide();
        return review.toResponse();
    }

    @Override
    public ReviewResponse get(long reviewId) {
        return reviewRepository.findById(reviewId).get().toResponse();
    }

    @Override
    public ReviewResponse find(long waitingId) {
        Waiting waiting = waitingRepository.findById(waitingId).get();
        return reviewRepository.findByWaitingOrderByIdDesc(waiting).get().toResponse();
    }

    @Override
    public List<ReviewResponse> getList(long storeId) {
        Store store = storeRepository.findById(storeId).get();
        return reviewRepository.findAllByStoreAndDeletedIsFalse(store).stream().map(Review::toResponse).collect(Collectors.toList());
    }
}
