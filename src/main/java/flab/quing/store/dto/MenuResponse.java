package flab.quing.store.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class MenuResponse {

    private long menuId;

    private String name;

    private String imageUrl;

    private Integer price;

}
