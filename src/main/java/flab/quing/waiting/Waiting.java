package flab.quing.waiting;

import flab.quing.entity.BaseEntity;
import flab.quing.store.Store;
import flab.quing.user.User;
import flab.quing.waiting.dto.WaitingResponse;
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


    public static Waiting of(User user, Store store) {
        return Waiting.builder()
                .user(user)
                .store(store)
                .waitingQueueStatus(WaitingQueueStatus.WAITING)
                .build();
    }

    public WaitingResponse toResponse() {
        WaitingResponse waitingResponse = WaitingResponse.builder()
                .id(getId())
                .storeName(store.getName())
                .userName(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .waitingQueueStatus(waitingQueueStatus)
                .callCount(callCount)
                .build();

        return waitingResponse;
    }

    public void done() {
        waitingQueueStatus = WaitingQueueStatus.DONE;
    }
}
