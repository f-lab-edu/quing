package flab.quing.repository;

import flab.quing.domain.Member;
import flab.quing.domain.Store;
import flab.quing.domain.StoreRating;
import flab.quing.domain.StoreReview;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreRatingRepositoryTest {

    @Autowired StoreRatingRepository storeRatingRepository;
    @Autowired StoreReviewRepository storeReviewRepository;

    @Test
    void 가게별점등록_성공(){
        //given
        Store store = new Store();
        store.setName("추억닭발");
        store.setAddress("인천시 부평구");
        store.setOpeningHours("16:00~00:00");
        store.setPhone("010-1111-2222");
        store.setPageLink("http://test.co.kr");

        Member member = new Member();
        member.setName("yuseon");
        member.setPhone("010-1234-1111");
        member.setPassword("1q2w3e4r");

        StoreReview storeReview = new StoreReview();
        storeReview.setStore(store);
        storeReview.setMember(member);
        storeReview.setImage("food.jpg");
        storeReview.setMessage("맛있어요!");
        storeReview.setRating(3);
        storeReviewRepository.save(storeReview);

        List<StoreReview> storeReviews = storeReviewRepository.findAllByStore(store);
        Integer rating=0;
        for (StoreReview review:
             storeReviews) {
            rating += review.getRating();
        }
        rating /= storeReviews.size();

        StoreRating storeRating = new StoreRating();
        storeRating.setStore(store);
        storeRating.setRating(rating);

        //when
        storeRatingRepository.save(storeRating);

        //then
        Optional<StoreRating> storeRating1 = storeRatingRepository.findByStore(store);
        assertThat(storeRatingRepository.findAll().size()).isEqualTo(1);
        assertThat(storeRating1.get().getRating()).isEqualTo(3);
    }

}