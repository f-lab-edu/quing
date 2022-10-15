package flab.quing.review.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class RatingResponse {

    private long storeId;

    private String storeName;

    private float rating;

}
