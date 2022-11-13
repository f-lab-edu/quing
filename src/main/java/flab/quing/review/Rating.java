package flab.quing.review;

import flab.quing.entity.BaseEntity;
import flab.quing.store.Store;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Rating extends BaseEntity {

    @OneToOne
    private Store store;

    private float rating;

}
