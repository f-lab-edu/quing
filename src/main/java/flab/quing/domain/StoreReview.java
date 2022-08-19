package flab.quing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class StoreReview extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;
    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;
    private Integer rating;
    private String message;
    private String image;
}
