package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.user.User;
import flab.quing.waiting.WaitingUser;

import java.util.List;

public interface ReviewService {
    // ReviewService reviewService;
    // reviewService.create(aaaa);

    Long create(User user, WaitingUser userHistory, Rating rating, String message);

    Long update(User user, WaitingUser userHistory, Rating rating, String message);

    Long delete();

    Review get();

    List<Review> getList(Store store);


}
