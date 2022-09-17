package flab.quing.review.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ReviewRequest {

    @NonNull
    private long userId;

    @NonNull
    private long waitingId;

    private int rating;

    private String message;

    private String image;
}
