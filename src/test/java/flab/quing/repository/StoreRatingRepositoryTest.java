package flab.quing.repository;

import flab.quing.user.User;
import flab.quing.store.Store;
import flab.quing.review.Rating;
import flab.quing.review.Review;
import flab.quing.review.RatingRepository;
import flab.quing.review.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StoreRatingRepositoryTest {

    @Autowired
    RatingRepository storeRatingRepository;
    @Autowired
    ReviewRepository storeReviewRepository;

    @Test
    void 가게별점등록_성공() {
        //given
        Store store = new Store();
        store.setName("추억닭발");
        store.setAddress("인천시 부평구");
        store.setOpeningHours("16:00~00:00");
        store.setPhone("010-1111-2222");
        store.setPageLink("http://test.co.kr");

        User member = new User();
        member.setName("yuseon");
        member.setPhone("010-1234-1111");
        member.setPassword("1q2w3e4r");

        Review storeReview = new Review();
        storeReview.setStore(store);
        storeReview.setMember(member);
        storeReview.setImage("food.jpg");
        storeReview.setMessage("맛있어요!");
        storeReview.setRating(3);
        storeReviewRepository.save(storeReview);

        List<Review> storeReviews = storeReviewRepository.findAllByStore(store);
        Float rating = 0f;
        for (Review review :
                storeReviews) {
            rating += review.getRating();
        }
        rating /= storeReviews.size();

        Rating storeRating = new Rating();
        storeRating.setStore(store);
        storeRating.setRating(rating);

        //when
        storeRatingRepository.save(storeRating);

        //then
        Optional<Rating> storeRating1 = storeRatingRepository.findByStore(store);
        assertThat(storeRatingRepository.findAll().size()).isEqualTo(1);
        assertThat(storeRating1.get().getRating()).isEqualTo(3);
    }

}