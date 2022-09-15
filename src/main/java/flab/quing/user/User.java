package flab.quing.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class User extends BaseEntity{
    private String name;
    private String password;
    private String phone;
}

