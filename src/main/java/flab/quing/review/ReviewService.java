package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.user.User;
import flab.quing.waiting.WaitingUser;

import java.util.List;

public interface ReviewService {

    Long create(User user, WaitingUser userHistory, Rating rating, String message);
//userid, hitory id, ... request class
    Long update(User user, WaitingUser userHistory, Rating rating, String message);
//upsert,, .save
    Long delete(id);

    Review get(id);

    List<Review> getList(Store store);





}
