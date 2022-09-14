package flab.quing.store;

import flab.quing.user.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Store extends BaseEntity {
    private String name;
    private String phone;
    private String openingHours;
    private String address;
    private String pageLink;
}
