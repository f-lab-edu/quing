package flab.quing.waiting;

import flab.quing.store.Store;
import flab.quing.entity.BaseEntity;
import flab.quing.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Waiting extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private int callCount;

    private WaitingQueueStatus waitingQueueStatus;

}
