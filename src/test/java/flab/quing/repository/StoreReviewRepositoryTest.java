package flab.quing.repository;

import flab.quing.domain.Member;
import flab.quing.domain.Store;
import flab.quing.domain.StoreReview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreReviewRepositoryTest {

    @Autowired StoreReviewRepository storeReviewRepository;

    @Test
    void 가게리뷰등록_성공(){
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

        //when
        storeReviewRepository.save(storeReview);

        //then
        List<StoreReview> storeReviews = storeReviewRepository.findAllByStore(store);
        assertThat(storeReviews.size()).isEqualTo(1);
        assertThat(storeReviews.get(0).getMessage()).isEqualTo(storeReview.getMessage());
        assertThat(storeReviews.get(0).getMessage()).isEqualTo("맛있어요!");

    }
}