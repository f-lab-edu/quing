package flab.quing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Menu extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;
    private String name;
    private Integer price;
    private String image;
}
