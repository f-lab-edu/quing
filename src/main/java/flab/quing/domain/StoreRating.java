package flab.quing.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StoreRating extends BaseEntity {
    @ManyToOne
    private Store store;
    private Integer rating;
}
