package flab.quing;

import flab.quing.review.Review;
import flab.quing.store.Store;
import flab.quing.user.User;
import flab.quing.waiting.Waiting;
import flab.quing.waiting.WaitingQueueStatus;

import java.util.List;

public class DummyDataMaker {

    static long userCount, storeCount, waitingCount, reviewCount;

    public static void init() {
        userCount = 0;
        storeCount = 0;
        waitingCount = 0;
        reviewCount = 0;
    }

    public static User user() {
        userCount++;
        User user = User.builder()
                .name("TestUser" + userCount)
                .phone("010-1234-" + String.format("%04d", userCount))
                .build();
        user.setId(userCount);
        return user;
    }

    public static User user(String name) {
        userCount++;
        User user = User.builder()
                .name(name)
                .phone("010-1234-" + String.format("%04d", userCount))
                .build();
        user.setId(userCount);
        return user;
    }

    public static Store store() {
        storeCount++;
        Store store = Store.builder()
                .name("TestStore" + storeCount)
                .phoneNumber("010-1234-" + String.format("%04d", storeCount))
                .address("서울특별시 강남구 강남대로 " + String.format("%04d", storeCount))
                .openHours("매일 09:00~18:00")
                .openStatus("영업중/마감")
                .pageLink("storeLink")
                .build();
        store.setId(storeCount);
        return store;
    }

    public static Waiting waiting(User user, Store store) {
        waitingCount++;
        Waiting waiting = Waiting.builder()
                .user(user)
                .store(store)
                .waitingQueueStatus(WaitingQueueStatus.WAITING)
                .callCount(0)
                .build();
        waiting.setId(waitingCount);
        return waiting;
    }

    public static Review review(User user, Waiting waiting) {
        reviewCount++;
        Review review = Review.builder()
                .user(user)
                .waiting(waiting)
                .message("review" + reviewCount)
                .rating(10)
                .imageUrls(List.of("link1", "link2"))
                .build();
        review.setId(reviewCount);
        return review;
    }

    public static Review review(User user, Waiting waiting, String message) {
        reviewCount++;
        Review review = Review.builder()
                .user(user)
                .waiting(waiting)
                .message(message)
                .rating(10)
                .imageUrls(List.of("link1", "link2"))
                .build();
        review.setId(reviewCount);
        return review;
    }
}
