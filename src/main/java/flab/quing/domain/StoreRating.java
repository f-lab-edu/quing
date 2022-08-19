package flab.quing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class StoreRating extends BaseEntity {
    @ManyToOne
    private Store store;
    private Integer rating;
}
