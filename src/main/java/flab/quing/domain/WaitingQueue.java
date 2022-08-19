package flab.quing.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class WaitingQueue extends BaseEntity {
    @ManyToOne
    private Member member;
    @ManyToOne
    private Store store;
    private Integer callCount;
    private WaitingQueueStatus waitingQueueStatus;
}
