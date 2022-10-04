package flab.quing.store;

import flab.quing.common.BaseEntity;
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
}
