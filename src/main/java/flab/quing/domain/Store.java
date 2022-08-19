package flab.quing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Store extends BaseEntity {
    private String name;
    private String phone;
    private String openingHours;
    private String address;
    private String pageLink;
}
