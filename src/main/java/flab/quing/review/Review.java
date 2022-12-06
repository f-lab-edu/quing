package flab.quing.review;

import flab.quing.entity.BaseEntity;
import flab.quing.review.dto.ReviewRequest;
import flab.quing.review.dto.ReviewResponse;
import flab.quing.user.User;
import flab.quing.waiting.Waiting;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

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
    private Waiting waiting;

    private int rating;

    private String message;

    @ElementCollection
    private List<String> imageUrls;


    public static Review of(User user, Waiting waiting, ReviewRequest reviewRequest) {
        return Review.builder()
                .user(user)
                .waiting(waiting)
                .rating(reviewRequest.getRating())
                .message(reviewRequest.getMessage())
                .imageUrls(reviewRequest.getImageUrls())
                .build();
    }

    public ReviewResponse toResponse() {
        return ReviewResponse.builder()
                .id(getId())
                .userId(user.getId())
                .userName(user.getName())
                .storeId(waiting.getStore().getId())
                .storeName(waiting.getStore().getName())
                .rating(rating)
                .message(message)
                .imageUrls(imageUrls)
                .build();
    }

    public void updateFrom(ReviewRequest reviewRequest) {
        rating = reviewRequest.getRating();
        message = reviewRequest.getMessage();
        imageUrls = reviewRequest.getImageUrls();
    }
}
