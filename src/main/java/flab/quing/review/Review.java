package flab.quing.review;

import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.store.Store;
import flab.quing.user.BaseEntity;
import flab.quing.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private int rating;

    private String message;

    private String imageUrl;

    public void of(ReviewRequest reviewRequest) {

        rating = reviewRequest.getRating();
        message = reviewRequest.getMessage();
        imageUrl = reviewRequest.getImageUrl();

    }

    public ReviewResponse toResponse() {
        ReviewResponse reviewResponse = ReviewResponse.builder()
                .userId(user.getId())
                .userName(user.getName())
                .storeId(store.getId())
                .storeName(store.getName())
                .rating(rating)
                .message(message)
                .imageUrl(imageUrl)
                .build();
        return reviewResponse;
    }

}
