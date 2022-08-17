package flab.quing.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;
    private String password;
    private String phone;
}
