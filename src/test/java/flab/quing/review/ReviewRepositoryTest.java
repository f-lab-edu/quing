package flab.quing.review;

import flab.quing.DummyDataMaker;
import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import flab.quing.waiting.Waiting;
import flab.quing.waiting.WaitingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    WaitingRepository waitingRepository;

    @Test
    void findTopByWaitingIdOrderByIdDesc() {
        User user = userRepository.save(DummyDataMaker.user());
        Store store = storeRepository.save(DummyDataMaker.store());
        Waiting waiting = waitingRepository.save(DummyDataMaker.waiting(user, store));

        Review review1 = reviewRepository.save(DummyDataMaker.review(user, waiting));
        log.info("review1 = " + review1);

        Review result1 = reviewRepository.findTopByWaitingIdOrderByIdDesc(waiting.getId()).get();
        assertThat(result1.getMessage()).isEqualTo(result1.getMessage());

        result1.hide();

        Review review2 = DummyDataMaker.review(user, waiting, "review2");
        reviewRepository.save(review2);

        Review result2 = reviewRepository.findTopByWaitingIdOrderByIdDesc(waiting.getId()).get();
        assertThat(result2.getMessage()).isEqualTo(review2.getMessage());
    }

    @Test
    void findAllByWaitingStoreIdAndDeletedIsFalse() {
        //given
        User user1 = userRepository.save(DummyDataMaker.user());
        User user2 = userRepository.save(DummyDataMaker.user());

        Store store = storeRepository.save(DummyDataMaker.store());

        Waiting waiting1 = waitingRepository.save(DummyDataMaker.waiting(user1, store));
        Waiting waiting2 = waitingRepository.save(DummyDataMaker.waiting(user2, store));

        Review review1 = reviewRepository.save(DummyDataMaker.review(user1, waiting1, "review1"));
        log.info("review1 = " + review1);

        Review review2 = reviewRepository.save(DummyDataMaker.review(user2, waiting2, "review2"));
        log.info("review2 = " + review2.getDeleted_at());

        review2.hide();
        log.info("review2 = " + review2.getDeleted_at());

        Review review3 = reviewRepository.save(DummyDataMaker.review(user2, waiting2, "review3"));
        log.info("review3 = " + review3);

        storeRepository.findAll().forEach(o-> log.info(o.getId().toString()+" "+o));

        //when
        List<Review> storeReviews = reviewRepository.findAllByWaitingStoreIdAndDeletedIsFalse(store.getId());
        log.info("allByWaitingStoreIdAndDeletedIsFalse = " + storeReviews);

        //then
        assertThat(storeReviews.size()).isEqualTo(2);
        assertThat(storeReviews.get(0).getMessage()).isEqualTo(review1.getMessage());
        assertThat(storeReviews.get(1).getMessage()).isEqualTo(review3.getMessage());
    }
}