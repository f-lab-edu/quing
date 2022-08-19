package flab.quing.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Menu extends BaseEntity{
    @ManyToOne
    private Store store;
    private String name;
    private Integer price;
    private String image;
}
