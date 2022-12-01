package flab.quing.review;

import flab.quing.DummyDataMaker;
import flab.quing.review.dto.ReviewPostRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.StoreManager;
import flab.quing.user.StoreManagerRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.Waiting;
import flab.quing.waiting.WaitingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {

    @Autowired
    ReviewController reviewController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    WaitingRepository waitingRepository;

    @Autowired
    StoreManagerRepository storeManagerRepository;


    @Test
    void test() {
        User user = userRepository.save(DummyDataMaker.user());
        Store store = storeRepository.save(DummyDataMaker.store());
        Waiting waiting = waitingRepository.save(DummyDataMaker.waiting(user, store));
        StoreManager storeManager = storeManagerRepository.save(
                StoreManager.builder()
                        .store(store)
                        .name("스토어매니저")
                        .build());

        ReviewPostRequest reviewPostRequest = ReviewPostRequest.builder()
                .rating(10)
                .message("테스트메시지")
                .imageUrls(List.of("https://chanwookim.me/agumon-dday/agumon.png"))
                .build();

        ReviewResponse reviewPostResponse = reviewController.postReview(user.toResponse(), reviewPostRequest);
        assertThat(reviewPostResponse.getMessage()).isEqualTo("테스트메시지");

        List<ReviewResponse> userReviewListResponses = reviewController.userReviewList(user.toResponse());
        assertThat(userReviewListResponses.get(0).getMessage()).isEqualTo("테스트메시지");

        List<ReviewResponse> storeReviewResponses = reviewController.storeReviewList(storeManager.toResponse());
        assertThat(storeReviewResponses.get(0).getMessage()).isEqualTo("테스트메시지");

        ReviewResponse getReviewResponse = reviewController.getReview(reviewPostResponse.getId());
        assertThat(getReviewResponse.getMessage()).isEqualTo("테스트메시지");

        reviewController.deleteReview(user.toResponse(), reviewPostResponse.getId());

        assertThatThrownBy(() -> {
            reviewController.getReview(reviewPostResponse.getId());
        }).isInstanceOf(NoSuchReviewException.class);
    }

}