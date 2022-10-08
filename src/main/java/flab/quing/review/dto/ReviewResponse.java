package flab.quing.review.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ReviewResponse {

    private long userId;

    private String userName;

    private long storeId;

    private String storeName;

    private float rating;

    private String message;

    private List<String> imageUrls;
}
