package flab.quing.review.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ReviewDeleteRequest {

    private long userId;

    private long reviewId;
}
