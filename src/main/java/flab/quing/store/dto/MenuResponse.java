package flab.quing.store.dto;

import lombok.Getter;

@Getter
public class MenuResponse {

    private Long menuId;
    private String name;
    private String image;
    private Integer price;

    public MenuResponse(Long menuId, String name, String image, Integer price) {
        this.menuId = menuId;
        this.name = name;
        this.image = image;
        this.price = price;
    }
}
