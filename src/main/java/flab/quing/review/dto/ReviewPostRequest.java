package flab.quing.review.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ReviewPostRequest {

    private int rating;

    private String message;

    private List<String> imageUrls;

}
