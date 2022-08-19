package flab.quing.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
    private String name;
    private String password;
    private String phone;
}
