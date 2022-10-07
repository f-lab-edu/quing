package flab.quing.store.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class MenuResponse {

    private long menuId;

    private String name;

    private String imageUrl;

    private Integer price;

}
