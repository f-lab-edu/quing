package flab.quing;

import flab.quing.review.Review;
import flab.quing.store.Store;
import flab.quing.user.StoreManager;
import flab.quing.user.User;
import flab.quing.waiting.Waiting;
import flab.quing.waiting.WaitingQueueStatus;
import lombok.Builder;

import java.util.List;

public class DummyDataMaker {

    boolean withId;
    long userCount, storeCount, waitingCount, reviewCount, storeManagerCount;

    public void init() {
        userCount = 0;
        storeCount = 0;
        waitingCount = 0;
        reviewCount = 0;
        storeManagerCount = 0;
    }

    @Builder
    public DummyDataMaker(boolean withId) {
        this.withId = withId;
    }

    public User user() {
        userCount++;
        User user = User.builder()
                .name("TestUser" + userCount)
                .phoneNumber("010-1234-" + String.format("%04d", userCount))
                .build();

        if (withId) {
            user.setId(userCount);
        }
        return user;
    }

    public Store store() {
        storeCount++;
        Store store = Store.builder()
                .name("TestStore" + storeCount)
                .phoneNumber("010-1234-" + String.format("%04d", storeCount))
                .address("서울특별시 강남구 강남대로 " + String.format("%04d", storeCount))
                .openHours("매일 09:00~18:00")
                .openStatus("영업중/마감")
                .pageLink("storeLink")
                .build();

        if (withId) {
            store.setId(storeCount);
        }

        return store;
    }

    public Waiting waiting(User user, Store store) {
        waitingCount++;
        Waiting waiting = Waiting.builder()
                .user(user)
                .store(store)
                .waitingQueueStatus(WaitingQueueStatus.WAITING)
                .callCount(0)
                .build();

        if (withId) {
            waiting.setId(waitingCount);
        }

        return waiting;
    }

    public Review review(User user, Waiting waiting) {
        reviewCount++;
        Review review = Review.builder()
                .user(user)
                .waiting(waiting)
                .message("review" + reviewCount)
                .rating(10)
                .imageUrls(List.of("link1", "link2"))
                .build();

        if (withId) {
            review.setId(reviewCount);
        }

        return review;
    }

    public Review review(User user, Waiting waiting, String message) {
        reviewCount++;
        Review review = Review.builder()
                .user(user)
                .waiting(waiting)
                .message(message)
                .rating(10)
                .imageUrls(List.of("link1", "link2"))
                .build();

        if (withId) {
            review.setId(reviewCount);
        }

        return review;
    }

    public StoreManager storeManager(Store store) {
        StoreManager storeManager = StoreManager.builder()
                .loginId("testStoreManager")
                .encryptedPassword("b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86")
                .name("홍길동")
                .phoneNumber("010-1234-" + String.format("%04d", storeManagerCount))
                .store(store)
                .build();
        storeManager.setId(storeManagerCount);

        if (withId) {
            storeManager.setId(storeManagerCount);
        }
        return storeManager;
    }
}
