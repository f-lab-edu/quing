package flab.quing.store;

import flab.quing.common.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Store extends BaseEntity {

    private String name;
    private String phoneNumber;
    private String openStatus;
    private String openHours;
    private String address;
    private String pageLink;
}
