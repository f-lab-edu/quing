package flab.quing.review.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ReviewRequest {

    private long userId;

    private long waitingId;

    private int rating;

    private String message;

    private String image;
}
