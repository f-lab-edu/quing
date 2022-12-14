package flab.quing.review;

import flab.quing.DummyDataMaker;
import flab.quing.review.dto.ReviewDeleteRequest;
import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
import flab.quing.waiting.Waiting;
import flab.quing.waiting.WaitingQueueStatus;
import flab.quing.waiting.WaitingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @InjectMocks
    ReviewServiceImpl reviewService;

    @Mock
    ReviewRepository reviewRepository;
    @Mock
    StoreRepository storeRepository;
    @Mock
    WaitingRepository waitingRepository;

    DummyDataMaker dummyDataMaker = DummyDataMaker.builder().withId(true).build();


    @Test
    void create_성공() {
        //given
        User user = User.builder()
                .name("김땡땡")
                .phoneNumber("010-9999-9999")
                .build();
        user.setId(1L);

        Store store = Store.builder()
                .name("가게1")
                .phoneNumber("02-1234-1234")
                .openStatus("영업중")
                .openHours("매일 11:00~23:00")
                .address("서울특별시 성동구 왕십리로 1")
                .pageLink("https://github.com/f-lab-edu/quing")
                .build();
        store.setId(1L);

        Waiting waiting = Waiting.builder()
                .user(user)
                .store(store)
                .waitingQueueStatus(WaitingQueueStatus.WAITING)
                .build();
        waiting.setId(1L);

        ReviewRequest reviewRequest = ReviewRequest.builder()
                .userId(waiting.getUser().getId())
                .waitingId(waiting.getStore().getId())
                .rating(3)
                .message("그냥그래요")
                .imageUrls(List.of("https://user-images.githubusercontent.com/22807730/191471325-862134f8-6d8f-4062-9b12-ecd541a6323a.png"))
                .build();

        Review review = Review.of(user, waiting, reviewRequest);
        review.setId(1L);

        when(waitingRepository.findById(anyLong())).thenReturn(Optional.of(waiting));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        //when
        ReviewResponse reviewResponse = reviewService.create(reviewRequest);

        //then
        assertThat(reviewResponse.getUserName()).isEqualTo("김땡땡");
        assertThat(reviewResponse.getStoreName()).isEqualTo("가게1");
        assertThat(reviewResponse.getMessage()).isEqualTo("그냥그래요");
    }

    @Test
    void update() {
        User user = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting = dummyDataMaker.waiting(user, store);

        Review review1 = dummyDataMaker.review(user, waiting, "review1");
        Review review2 = dummyDataMaker.review(user, waiting, "review2");

        ReviewRequest reviewRequest1 = ReviewRequest.builder()
                .userId(user.getId())
                .waitingId(waiting.getId())
                .message("review1")
                .build();

        ReviewRequest reviewRequest2 = ReviewRequest.builder()
                .userId(user.getId())
                .waitingId(waiting.getId())
                .message("review2")
                .build();

        //create
        when(waitingRepository.findById(anyLong())).thenReturn(Optional.of(waiting));
        when(reviewRepository.save(any(Review.class)))
                .thenReturn(review1)    // 1st create
                .thenReturn(review2);   // 2nd create

        //update
        when(reviewRepository.findTopByWaitingIdOrderByIdDesc(waiting.getId())).thenReturn(Optional.of(review1));


        ReviewResponse createResponse = reviewService.create(reviewRequest1);
        ReviewResponse updateResponse = reviewService.update(reviewRequest2);

        assertThat(updateResponse.getMessage()).isEqualTo("review2");
    }

    @Test
    void hide() {
        //delete필드가 잘 바뀌는지?
        User user = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting = dummyDataMaker.waiting(user, store);
        Review review = dummyDataMaker.review(user, waiting);

        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));

        ReviewResponse response = reviewService.hide(
                ReviewDeleteRequest.builder()
                .reviewId(review.getId())
                .userId(user.getId())
                .build()
        );

        System.out.println("response = " + response);
        System.out.println("review = " + review.isDeleted());
        Assertions.assertThat(review.isDeleted()).isEqualTo(true);
    }

    @Test
    void get() {
        //review id로 review를 잘 가져오는지..
        User user = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting = dummyDataMaker.waiting(user, store);
        Review review = dummyDataMaker.review(user, waiting);

        when(reviewRepository.findByIdAndDeletedIsFalse(anyLong())).thenReturn(Optional.of(review));

        ReviewResponse reviewResponse = reviewService.getByReviewId(review.getId());
        System.out.println("reviewResponse = " + reviewResponse);
        assertThat(reviewResponse.getMessage()).isEqualTo(review.getMessage());
    }

    @Test
    void find() {

        User user = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting = dummyDataMaker.waiting(user, store);
        Review review = dummyDataMaker.review(user, waiting);

        when(reviewRepository.findTopByWaitingIdOrderByIdDesc(waiting.getId())).thenReturn(Optional.of(review));

        ReviewResponse response = reviewService.getByWaitingId(waiting.getId());
        System.out.println("response = " + response);

        assertThat(response).isEqualTo(review.toResponse());
    }

    @Test
    void getList() {
        User user1 = dummyDataMaker.user();
        User user2 = dummyDataMaker.user();
        Store store = dummyDataMaker.store();
        Waiting waiting1 = dummyDataMaker.waiting(user1, store);
        Waiting waiting2 = dummyDataMaker.waiting(user2, store);
        Review review1 = dummyDataMaker.review(user1, waiting1);
        Review review2 = dummyDataMaker.review(user2, waiting2);

        when(reviewRepository.findAllByWaitingStoreIdAndDeletedIsFalse(store.getId())).thenReturn(List.of(review1, review2));

        List<ReviewResponse> responseList = reviewService.getListByStoreId(store.getId());
        responseList.forEach(System.out::println);

        assertThat(responseList.size()).isEqualTo(2);
        assertThat(responseList.get(0).getStoreName()).isEqualTo(store.getName());
    }
}
