package flab.quing.user;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends BaseEntity {
    private String name;
    private String phone;
}

