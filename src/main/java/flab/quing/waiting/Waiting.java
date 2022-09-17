package flab.quing.waiting;

import flab.quing.user.BaseEntity;
import flab.quing.user.User;
import flab.quing.store.Store;
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
public class Waiting extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private User member;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private Integer callCount;

    private WaitingQueueStatus waitingQueueStatus;

}
