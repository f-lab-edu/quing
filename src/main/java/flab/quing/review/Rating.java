package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.user.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Rating extends BaseEntity {
    @ManyToOne
    private Store store;
    private Integer rating;
}
