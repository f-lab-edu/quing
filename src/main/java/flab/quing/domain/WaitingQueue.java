package flab.quing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class WaitingQueue extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;
    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;
    private Integer callCount;
    private WaitingQueueStatus waitingQueueStatus;
}
