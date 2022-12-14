package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
import flab.quing.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Menu extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;
    private String name;
    private Integer price;
    private String imageUrl;

    public MenuResponse toResponse() {
        MenuResponse menuResponse = MenuResponse.builder()
                .menuId(getId())
                .name(name)
                .price(price)
                .imageUrl(imageUrl)
                .build();
        return menuResponse;
    }

    public void update(MenuRequest menuRequest) {
        name = menuRequest.getName();
        price = menuRequest.getPrice();
        imageUrl = menuRequest.getImageUrl();
    }
}
