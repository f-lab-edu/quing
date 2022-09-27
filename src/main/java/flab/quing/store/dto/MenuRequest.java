package flab.quing.store.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class MenuRequest {

    private String name;

    private String imageUrl;

    private Integer price;

}
