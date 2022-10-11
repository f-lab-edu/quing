package flab.quing.review;

import flab.quing.store.Store;
import flab.quing.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Rating extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Store store;

    private float rating;

}
