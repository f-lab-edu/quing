package flab.quing.review;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.user.User;
import flab.quing.waiting.Waiting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;


    @Test
    void findByWaitingOrderByIdDesc() {

        User user = DummyDataMaker.user();
        Store store = DummyDataMaker.store();
        Waiting waiting = DummyDataMaker.waiting(user, store);

        Review review1 = DummyDataMaker.review(user, waiting, "review1");
        reviewRepository.save(review1);

        Review result1 = reviewRepository.findTopByWaitingOrderByIdDesc(waiting).get();
        assertThat(result1.getMessage()).isEqualTo("review1");

        result1.hide();

        Review review2 = DummyDataMaker.review(user, waiting, "review2");
        reviewRepository.save(review2);

        Review result2 = reviewRepository.findTopByWaitingOrderByIdDesc(waiting).get();
        assertThat(result2.getMessage()).isEqualTo("review2");
    }

    @Test
    void findAllByWaitingStoreAndDeletedIsFalse() {
        User user1 = DummyDataMaker.user();
        User user2 = DummyDataMaker.user();

        Store store = DummyDataMaker.store();

        Waiting waiting1 = DummyDataMaker.waiting(user1, store);
        Waiting waiting2 = DummyDataMaker.waiting(user2, store);

        Review review1 = DummyDataMaker.review(user1, waiting1, "review1");
        Review review2 = DummyDataMaker.review(user1, waiting2, "review2");

        Review result1 = reviewRepository.save(review1);
        Review result2 = reviewRepository.save(review2);
        result2.hide();
        Review review3 = DummyDataMaker.review(user1, waiting2, "review3");
        Review result3 = reviewRepository.save(review3);

        List<Review> storeReviews = reviewRepository.findAllByWaitingStoreAndDeletedIsFalse(store);

        storeReviews.stream().forEach(System.out::println);

        assertThat(storeReviews.size()).isEqualTo(2);
        assertThat(storeReviews.get(0).getMessage()).isEqualTo("review1");
        assertThat(storeReviews.get(1).getMessage()).isEqualTo("review3");
    }
}