package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.user.BaseEntity;
import flab.quing.user.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private int rating;

    private String message;

    private String image;

}
