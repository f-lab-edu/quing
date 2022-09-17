package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.user.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating extends BaseEntity {

    @ManyToOne
    private Store store;

    private float rating;
}
