package flab.quing.review;

import flab.quing.review.dto.*;
import flab.quing.store.*;
import flab.quing.user.*;
import flab.quing.waiting.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final WaitingRepository waitingRepository;


    @Transactional
    @Override
    public ReviewResponse create(ReviewRequest reviewRequest) {
        Waiting waiting = waitingRepository.findById(reviewRequest.getWaitingId());

        User user = waiting.getUser();
        Store store = waiting.getStore();

        Review review = Review.builder()
                .user(user)
                .store(store)
                .build();
        review.of(reviewRequest);


        Review save = reviewRepository.save(review);
        return save.toResponse();
    }

    @Transactional
    @Override
    public ReviewResponse update(ReviewRequest reviewRequest) {
        return create(reviewRequest);
    }

    @Transactional
    @Override
    public ReviewResponse hide(long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        review.setDeleted(true);
        review.setDeleted_at(LocalDateTime.now());
        return review.toResponse();
    }

    @Override
    public ReviewResponse get(long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return review.toResponse();
    }

    @Override
    public List<ReviewResponse> getList(long storeId) {
        Store store = storeRepository.findById(storeId).get();
        return reviewRepository.findAllByStore(store).stream().map(Review::toResponse).collect(Collectors.toList());
    }
}
