package flab.quing.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StoreReview extends BaseEntity {
    @ManyToOne
    private Member member;
    @ManyToOne
    private Store store;
    private String message;
    private String image;
}
