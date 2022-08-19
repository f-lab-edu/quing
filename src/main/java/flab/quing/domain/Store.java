package flab.quing.domain;

import javax.persistence.Entity;

@Entity
public class Store extends BaseEntity {
    private String name;
    private String phone;
    private String openingHours;
    private String address;
    private String pageLink;
}
