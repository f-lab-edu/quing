package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.user.BaseEntity;
import flab.quing.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Review extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private User member;
    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;
    private Integer rating;
    private String message;
    private String image;
}
