package flab.quing.review.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class RatingResponse {

    private long storeId;

    private String storeName;

    private float rating;

}
