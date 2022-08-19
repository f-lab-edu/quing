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
    @ManyToOne
    private Member member;
    @ManyToOne
    private Store store;
    private String message;
    private String image;
}
